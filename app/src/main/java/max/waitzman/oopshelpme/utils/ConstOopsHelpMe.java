package max.waitzman.oopshelpme.utils;

import android.content.Context;

import java.util.ArrayList;
import java.util.regex.Pattern;

//import max.waitzman.oopshelpme.ApplicationMy;
import max.waitzman.oopshelpme.R;

public class ConstOopsHelpMe {
	public ConstOopsHelpMe() {
	}
	public Context context;
	public ConstOopsHelpMe(Context context) {
		this.context=context;
	}

	public static final String LOG_CONST = "Meteo";
	//public static final String LOG = ApplicationMy.getAppContext().getString(R.string.app_name);

	public static final String SHARED_PREFS_APPKEY = "Vaymaks";


	public static final class Builds{
		public static final String MeteoIsrael ="MeteoIsrael";

		public static final String appollution="appollution";
		public static final String Wisconsin ="Wisconsin";
		public static final String UKAir="UKAir";
		public static final String WisconsinGoogle="WisconsinGoogle";
		public static final String sample="";

	}

	public static final class RequestCodes{
		public static final int PLAY_SERVICES_UPDATE=100;
		public static final int AboutTahana=11;
		public static final int ActivitySettings=22;
	}

	public static final class Patterns{
		public static final Pattern PORT = Pattern.compile("(\\d+)");
		public static final Pattern LATITUDE = Pattern.compile("^(\\+|-)?(?:90(?:(?:\\.0{1,6})?)|(?:[0-9]|[1-8][0-9])(?:(?:\\.[0-9]{1,6})?))$");
		public static final Pattern LONGTITUDE = Pattern.compile("^(\\+|-)?(?:180(?:(?:\\.0{1,6})?)|(?:[0-9]|[1-9][0-9]|1[0-7][0-9])(?:(?:\\.[0-9]{1,6})?))$");
		public static final Pattern TIMEBASE = Pattern.compile("(60|[0-5][1-9]?)");

		//public static final int Monitor = 300;
	}

	public enum MapFactory {
		MapGoogle( "MapGoogle" ),
		MapEsri( "MapEsri" );
		private String mapName;
		MapFactory(String mapName ) {
			//MapFactory(int reportId , String mapName ) {
			//this.reportId = reportId;
			this.mapName = mapName;
		}
		@Override
		public String toString() {
			return mapName;
		}
		/*public String nameReport() {
			return name;
		}*/
	}

	/*public enum Reports {
		reports_monitoring_data_1h(0 , ApplicationMy.getContext().getString(R.string.reports_monitoring_data_1h)   , String.valueOf(R.drawable.reports_monitoring_data_1h)),
		reports_aqi_avg_1h(1 ,ApplicationMy.getContext().getString(R.string.reports_aqi_avg_1h), String.valueOf(R.drawable.reports_aqi_avg_1h)),
		reports_ozone_avg_8h(2 , ApplicationMy.getContext().getString(R.string.reports_ozone_avg_8h) , String.valueOf(R.drawable.reports_ozone_avg_8h)),
		reports_pm25_avg_24h(3 , ApplicationMy.getContext().getString(R.string.reports_pm25_avg_24h) , String.valueOf(R.drawable.reports_pm25_avg_24h)),
		reports_aqi_highest(4 , ApplicationMy.getContext().getString(R.string.reports_aqi_highest) , String.valueOf(R.drawable.reports_aqi_highest) ),
		reports_pm25_highest(5 , ApplicationMy.getContext().getString(R.string.reports_pm25_highest) , String.valueOf(R.drawable.reports_pm25_highest) ),
		reports_ozone_highest(6 , ApplicationMy.getContext().getString(R.string.reports_ozone_highest) , String.valueOf(R.drawable.reports_ozone_highest) );
		private String name;
		private int reportId;
		private String drawableResId;
		private Reports(int reportId , String name , String drawableResId ) {
			this.reportId = reportId;
			this.name = name;
			this.drawableResId=drawableResId;
		}
		@Override
		public String toString() {
			return name;
		}
		public String nameReport() {
			return name;
		}
		public Integer reportId() {
			return reportId;
		}
		public String drawableResId() {
			return drawableResId;
		}
		public static ArrayList<Madad> getAllReports (){
			ArrayList<Madad> listReports=new ArrayList<>();
			for(Const.Reports report : Const.Reports.values()) {
				listReports.add(new Madad(report.reportId,report.nameReport(),report.drawableResId()));
			}
			*//*listReports.add(new Madad(0 , String.valueOf(R.string.reports_monitoring_data_1h) , String.valueOf(R.drawable.reports_monitoring_data_1h)));
			listReports.add(new Madad(1 , String.valueOf(R.string.reports_aqi_avg_1h),String.valueOf(R.drawable.reports_aqi_avg_1h) ));
			listReports.add(new Madad(2 , String.valueOf(R.string.reports_ozone_avg_8h) , String.valueOf(R.drawable.reports_ozone_avg_8h)));
			listReports.add(new Madad(3 , String.valueOf(R.string.reports_pm25_avg_24h) ,String.valueOf(R.drawable.reports_pm25_avg_24h)));
			listReports.add(new Madad(4 , String.valueOf(R.string.reports_aqi_highest) ,String.valueOf(R.drawable.reports_aqi_highest)));
			listReports.add(new Madad(5 , String.valueOf(R.string.reports_pm25_highest) ,String.valueOf(R.drawable.reports_pm25_highest)));
			listReports.add(new Madad(6 , String.valueOf(R.string.reports_ozone_highest) ,String.valueOf(R.drawable.reports_ozone_highest)));*//*
			*//*for (int i = 0; i < list.size(); i++) {
				if(list.get(i).getId()==devTypeInt){
					return list.get(i).getName();
				}
			}*//*
			return listReports;
		}

		public static Const.Reports  findReportByRepId (int repId){
			for(Const.Reports report : Const.Reports.values()) {
				if (report.reportId()==repId) {
					return report;
				}
			}
			return null;
		}

		*//*
		public  Madad  findReportByRepId (int repId){
			ArrayList<Madad> listReports=new ArrayList<>();
			listReports.add(new Madad(0 , String.valueOf(R.string.reports_monitoring_data_1h) , String.valueOf(R.drawable.reports_monitoring_data_1h)));
			listReports.add(new Madad(1 , String.valueOf(R.drawable.reports_aqi_avg_1h),String.valueOf(R.drawable.reports_aqi_avg_1h) ));
			listReports.add(new Madad(2 , String.valueOf(R.drawable.reports_ozone_avg_8h) , String.valueOf(R.drawable.reports_ozone_avg_8h)));
			listReports.add(new Madad(3 , String.valueOf(R.drawable.reports_pm25_avg_24h) ,String.valueOf(R.drawable.reports_pm25_avg_24h)));
			listReports.add(new Madad(4 , String.valueOf(R.drawable.reports_aqi_highest) ,String.valueOf(R.drawable.reports_aqi_highest)));
			listReports.add(new Madad(5 , String.valueOf(R.drawable.reports_pm25_highest) ,String.valueOf(R.drawable.reports_pm25_highest)));
			listReports.add(new Madad(6 , String.valueOf(R.drawable.reports_ozone_highest) ,String.valueOf(R.drawable.reports_ozone_highest)));
			for (int i = 0; i < listReports.size(); i++) {
				if(listReports.get(i).getMadadId()==repId){
					//new Const.Reports(listReports.get(i).getMadadId(),listReports.get(i).getMadadName(),listReports.get(i).getMadadDescription());
					return listReports.get(i);
				}
			}
			return null;
		}
		*//*

		*//*Const.DevicesEnum Like;
		Like=Const.DevicesEnum.EnviDaq8080;
		switch (Like){
			case EnviDaq8017:
			{
			}
		}*//*

	}*/
	
	public static final class Preferences{
		public static final String IndNum="IndNum";
		public static final String IndSmile="IndSmile";
        public static final String IndexAs="IndexAs";
            public static final int IndexAsSmile=1;
            public static final int IndexAsNum=2;
        public static final String ScreenStart="ScreenStart";
            public static final int ScreenStartMap=1;
            public static final int ScreenStartMyStations=2;
		public static final String TILE_PROVIDER="TILE_PROVIDER";
			public static final int MAP_TYPE_LAST_USED =55;
		public static final String LocStart="LocStart";
			public static final int LocStartMyPosition=50;
			public static final int LocStartFavoriteStat=51;
			public static final int LocStartLastPosition=52;
        public static final String Lang="Lang";
            public static final int en_int=1;
            public static final int he_int=2;
            public static final String en="en";
			public static final String he="iw";
            //public static final String he="he";
		public static final String DefaultStation="DefaultStation";

	}

    public static final class Db{
        public static final String DB_NAME="OopsHelpMe.db";
        public static final int DB_VERSION = 1;

    }
		
}
