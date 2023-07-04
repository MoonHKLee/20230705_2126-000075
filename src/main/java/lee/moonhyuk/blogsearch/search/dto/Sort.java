package lee.moonhyuk.blogsearch.search.dto;

public enum Sort {
	ACCURACY("accuracy", "sim"),
	RECENCY("recency", "date");

	private final String kakao;
	private final String naver;

	Sort(String kakao, String naver) {
		this.kakao = kakao;
		this.naver = naver;
	}

	public String getKakao() {
		return kakao;
	}

	public String getNaver() {
		return naver;
	}
}