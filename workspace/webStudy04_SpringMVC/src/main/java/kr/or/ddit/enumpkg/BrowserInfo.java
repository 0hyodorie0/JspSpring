package kr.or.ddit.enumpkg;

public enum BrowserInfo {
//	final String EDG="EDG";
//	final BrowserInfo EDG = new BrowserInfo("엣지");
	EDG("엣지"), CHROME("크롬"), SAFARI("사파리"), OTHER("기타");
	
	private BrowserInfo(String browserName) {
		this.browserName = browserName;
	}

	private String browserName;
	
	public String getBrowserName() {
		return browserName;
	}
	
	public static String findBrowser(String agent) {
		agent = agent.toUpperCase();
		String browser = null;
		BrowserInfo info = BrowserInfo.OTHER; 
		for( BrowserInfo tmp : BrowserInfo.values()){
			if(agent.contains(tmp.name())){
				info = tmp;
				break;
			}
		}
		browser = info.getBrowserName();
		return browser;
	}
}









