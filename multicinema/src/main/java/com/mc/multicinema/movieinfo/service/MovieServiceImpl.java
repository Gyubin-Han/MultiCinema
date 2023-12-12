package com.mc.multicinema.movieinfo.service;

import java.io.BufferedInputStream;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mc.multicinema.moviecomment.dao.MovieCommentDAO;
import com.mc.multicinema.moviecomment.dto.MovieCommentDTO;
import com.mc.multicinema.movieinfo.dao.MovieDAO;
import com.mc.multicinema.movieinfo.dto.DailyBoxOfficeDTO;
import com.mc.multicinema.movieinfo.dto.MovieDTO;



@Service
public class MovieServiceImpl implements MovieService{
	
	@Autowired
	DailyBoxOfficeDTO dto;
	@Autowired
	MovieCommentDAO mcommentdao;
	@Autowired
	MovieDTO moviedto;
	@Autowired
	MovieDAO moviedao;
	
	public ArrayList<DailyBoxOfficeDTO> dailyBoxOffice() {
		System.out.println("==============================service=======================================");
		
		ArrayList<DailyBoxOfficeDTO> list = new ArrayList<DailyBoxOfficeDTO>();
		try {
			JSONParser jsonparser = new JSONParser();

			JSONObject boxOfficeObj = (JSONObject) jsonparser.parse(readBoxOfficeUrl());
			JSONObject json1 = (JSONObject) boxOfficeObj.get("boxOfficeResult");
			JSONArray array1 = (JSONArray) json1.get("dailyBoxOfficeList");

			for (int i = 0; i < array1.size(); i++) {
				System.out.println("==============================service=======================================");
				
				JSONObject entity = (JSONObject) array1.get(i);
				String movieNm = (String) entity.get("movieNm");
				if (movieNm.length() >= 13) {
					String tmp = movieNm.substring(0, 11) + "..";
					movieNm = tmp;
				}

				String movieCd_str = (String) entity.get("movieCd");
				String rank_str = (String) entity.get("rank");
				String audiAcc_str = (String) entity.get("audiAcc");// 누적 관객수

				int movieCd = Integer.parseInt(movieCd_str);
				int rank = Integer.parseInt(rank_str);
				int audiAcc = Integer.parseInt(audiAcc_str);

				JSONObject movieInfoObj = (JSONObject) jsonparser.parse(readMovieInfoUrl(movieCd));
				JSONObject json2 = (JSONObject) movieInfoObj.get("movieInfoResult");
				JSONObject json3 = (JSONObject) json2.get("movieInfo");
				JSONArray array2 = (JSONArray) json3.get("audits");
				JSONObject entity2 = (JSONObject) array2.get(0);

				String tm = (String) entity2.get("watchGradeNm");
				String grade = tm.substring(0, 2);
				if (grade.equals("전체")) {
					grade = "ALL";
				}
					
				String movieImgSrc = moviedao.selectMovieImgSrc(movieCd);
				double avgScore = mcommentdao.movieAvgScore(movieCd);
				
				double avgScoreRound= Math.round(avgScore * 100)/100.0;
				System.out.println("ser 작동===============");
				
				DailyBoxOfficeDTO tmp = (DailyBoxOfficeDTO) dto.clone();

				tmp.setMovie_title(movieNm);
				tmp.setMovie_cd(movieCd);
				tmp.setRank(rank);
				tmp.setMovie_limitAge(grade);
				tmp.setAudiAcc(audiAcc);
				tmp.setMovie_img_src(movieImgSrc);
				tmp.setScore(avgScoreRound);

				list.add(tmp);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	public MovieDTO selectMovieDetailInfo(String movie_cd) {
		
		return moviedao.selectOneMovie(movie_cd);
	}
	
	//@PostConstruct
	public void ApiToDB() {
		JSONParser jsonparser = new JSONParser();
			System.out.println("==================DB업데이트 동작 중====================");
		try {JSONObject boxOfficeObj = (JSONObject) jsonparser.parse(readBoxOfficeUrl());
			JSONObject json1 = (JSONObject) boxOfficeObj.get("boxOfficeResult");
			JSONArray array1 = (JSONArray) json1.get("dailyBoxOfficeList");
			
			for (int i = 0; i < array1.size(); i++) {
				JSONObject entity = (JSONObject) array1.get(i);
				
				String movieNm = (String) entity.get("movieNm");
				String movieCd_str = (String) entity.get("movieCd");
				String movie_openDt = (String) entity.get("openDt");
				
				int movieCd = Integer.parseInt(movieCd_str);
				JSONParser jsonparser2 = new JSONParser();
				JSONObject movieInfoObj = (JSONObject) jsonparser2.parse(readMovieInfoUrl(movieCd));
				JSONObject json2 = (JSONObject) movieInfoObj.get("movieInfoResult");
				JSONObject json3 = (JSONObject) json2.get("movieInfo");
				JSONArray array2 = (JSONArray) json3.get("audits");
				JSONArray array3 = (JSONArray) json3.get("genres");
				JSONArray array4 = (JSONArray) json3.get("nations");
				JSONArray array5 = (JSONArray) json3.get("directors");
				
				String movie_type = (String) json3.get("typeNm");
				String movie_showTm = (String) json3.get("showTm");
				
				JSONObject entity2 = (JSONObject) array2.get(0);
				String tm = (String) entity2.get("watchGradeNm");
				String grade = tm.substring(0, 2);
				int age_limit = 0;
				if (grade.equals("전체")) {
					grade = "0";
					age_limit = Integer.parseInt(grade);
				}else {
					age_limit = Integer.parseInt(grade);
				}
				
				JSONObject entity3 = (JSONObject) array3.get(0);
				String movie_genreNm = (String) entity3.get("genreNm");
				
				JSONObject entity4 = (JSONObject) array4.get(0);
				String movie_makeNt = (String) entity4.get("nationNm");
				
				JSONObject entity5 = (JSONObject) array5.get(0);
				String director = (String) entity5.get("peopleNm");
				
				HashMap<String, String> map = posterAndPlot(movieNm, director);
				String poster = map.get("poster");
				String plot = map.get("plot");
				
				System.out.println(movieCd+"/"+ director+"/"+ movie_genreNm+"/"+ poster+"/"+ age_limit+"/"+ movie_makeNt 
						+"/"+movie_openDt+"/"+ plot+"/"+ movieNm+"/"+movie_type+"/"+ movie_showTm);
				
				moviedto.setMovie_cd(movieCd);
				moviedto.setMovie_director(director);
				moviedto.setMovie_genre(movie_genreNm);
				moviedto.setMovie_img_src(poster);
				moviedto.setMovie_limitAge(age_limit);
				moviedto.setMovie_makeNt(movie_makeNt);
				moviedto.setMovie_openDt(movie_openDt);
				moviedto.setMovie_spec(plot);
				moviedto.setMovie_title(movieNm);
				moviedto.setMovie_type(movie_type);
				moviedto.setMovie_showTm(movie_showTm);
				
				moviedao.insertOneMovie(moviedto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	
	@Override
	public HashMap<String ,ArrayList<String>> stillCutActorsService(String movie_title, String director) {
		JSONParser jsonparser = new JSONParser();
		ArrayList<String> stillCutList = new ArrayList<String>();
		ArrayList<String> actorList = new ArrayList<String>();
		HashMap<String,ArrayList<String>> map = new HashMap<String, ArrayList<String>>();
		try {
			
			JSONObject boxOfficeObj = (JSONObject) jsonparser.parse(readKoreaFilmUrl(movie_title, director));
			JSONArray array1 = (JSONArray) boxOfficeObj.get("Data");
			JSONObject json1 =  (JSONObject)array1.get(0);
			JSONArray array2 = (JSONArray) json1.get("Result");
			JSONObject json2 =  (JSONObject)array2.get(0);
			//스틸컷 리스트 만들기
			String stlls =  (String)json2.get("stlls");
			String[] arr = stlls.split("\\|");
			
			for(int i = 0; i < arr.length; i++) {
				System.out.println("스틸컷 반복문"+arr[i]);
				stillCutList.add(arr[i]);
			}
			//
			JSONObject json3 = (JSONObject)json2.get("actors");
			JSONArray arr3 = (JSONArray)json3.get("actor");
			
			for (int i = 0; i < 5; i++) {
				JSONObject tmp = (JSONObject)arr3.get(i);
				String actorNm = (String)tmp.get("actorNm");
				System.out.println("배우 이름 반복문"+actorNm);
				actorList.add(actorNm);
				
			}
			map.put("stillcutlist", stillCutList);
			map.put("actorlist", actorList);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		return map;
	}
	
	
	public HashMap<String, String> posterAndPlot(String movie_title, String director) {
		JSONParser jsonparser = new JSONParser();
		HashMap<String, String> map = new HashMap<String, String>();
		try {
			JSONObject KoreaFilmApi = (JSONObject) jsonparser.parse(readKoreaFilmUrl(movie_title, director));
			JSONArray karray1 = (JSONArray) KoreaFilmApi.get("Data");
			JSONObject kjson1 =  (JSONObject)karray1.get(0);
			JSONArray karray2 = (JSONArray) kjson1.get("Result");
			JSONObject kjson2 =  (JSONObject)karray2.get(0);
			JSONObject kjson3 =  (JSONObject)kjson2.get("plots");
			JSONArray karray3 = (JSONArray)kjson3.get("plot");
			JSONObject kjson4 = (JSONObject)karray3.get(0);
			
			String plotText = (String)kjson4.get("plotText");
			String posters =  (String)kjson2.get("posters");
			
			String[] arr = posters.split("\\|");
			
			
			map.put("poster", arr[0]);
			map.put("plot", plotText);
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		return map;
	}
	
	@Override
	public List<MovieDTO> selectAllmovie() {
		
		return moviedao.selectAllmovie();
		
	}
	
	
	private String readBoxOfficeUrl() throws Exception {
		BufferedInputStream reader = null;
		String key = "f0b5971de85c22ab8010d759968eef23";
		String targetDt = "";
		try {
			URL url = new URL("http://www.kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/"
					+ "searchDailyBoxOfficeList.json" + "?" + "key=" + key + "&targetDt=20231210");
			reader = new BufferedInputStream(url.openStream());
			StringBuffer buffer = new StringBuffer();
			int i;
			byte[] b = new byte[4096];
			while ((i = reader.read(b)) != -1) {
				buffer.append(new String(b, 0, i));
			}
			return buffer.toString();
		} finally {
			if (reader != null)
				reader.close();
		}
	}

	private String readMovieInfoUrl(int movie_cd) throws Exception {
		BufferedInputStream reader = null;
		String key = "f0b5971de85c22ab8010d759968eef23";
		try {
			URL url = new URL("http://www.kobis.or.kr/kobisopenapi/webservice/rest/movie/searchMovieInfo.json" + "?"
					+ "key=" + key + "&movieCd=" + movie_cd);
			reader = new BufferedInputStream(url.openStream());
			StringBuffer buffer = new StringBuffer();
			int i;
			byte[] b = new byte[4096];
			while ((i = reader.read(b)) != -1) {
				buffer.append(new String(b, 0, i));
			}
			return buffer.toString();
		} finally {
			if (reader != null)
				reader.close();
		}
	}
	
	private String readKoreaFilmUrl(String title, String director) throws Exception {
		BufferedInputStream reader = null;
		String key = "EVXDCBI8JN5T12ET284U";
		try {
			String encodeTitle = URLEncoder.encode(title, "UTF-8");
			String encodeDirector = URLEncoder.encode(director, "UTF-8");
			
			URL url = new URL("https://api.koreafilm.or.kr/openapi-data2/wisenut/search_api/search_json2.jsp?"
					+ "collection=kmdb_new2&detail=Y&ServiceKey="+key+"&title="+encodeTitle+"&director="+encodeDirector);
			reader = new BufferedInputStream(url.openStream());
			StringBuffer buffer = new StringBuffer();
			int i;
			byte[] b = new byte[4096];
			while ((i = reader.read(b)) != -1) {
				buffer.append(new String(b, 0, i));
			}
			return buffer.toString();
		} finally {
			if (reader != null)
				reader.close();
		}
	}

	

	

	
	

}

