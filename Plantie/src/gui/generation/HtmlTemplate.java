package gui.generation;

public class HtmlTemplate {
	private static final String htmlTemplate = "<!DOCTYPE html>\r\n" + "<html lang=\"en\">\r\n" + "<head>\r\n"
			+ "<meta charset=\"ISO-8859-1\">\r\n"
			+ "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\r\n"
			+ "<title>Plantie</title>\r\n" + "\r\n" + "<!-- Favicons -->\r\n"
			+ "<link rel=\"apple-touch-icon\" sizes=\"57x57\"\r\n" + "	href=\"res/apple-touch-icon-57x57.png\">\r\n"
			+ "<link rel=\"apple-touch-icon\" sizes=\"60x60\"\r\n" + "	href=\"res/apple-touch-icon-60x60.png\">\r\n"
			+ "<link rel=\"apple-touch-icon\" sizes=\"72x72\"\r\n" + "	href=\"res/apple-touch-icon-72x72.png\">\r\n"
			+ "<link rel=\"apple-touch-icon\" sizes=\"76x76\"\r\n" + "	href=\"res/apple-touch-icon-76x76.png\">\r\n"
			+ "<link rel=\"apple-touch-icon\" sizes=\"114x114\"\r\n"
			+ "	href=\"res/apple-touch-icon-114x114.png\">\r\n" + "<link rel=\"apple-touch-icon\" sizes=\"120x120\"\r\n"
			+ "	href=\"res/apple-touch-icon-120x120.png\">\r\n" + "<link rel=\"apple-touch-icon\" sizes=\"144x144\"\r\n"
			+ "	href=\"res/apple-touch-icon-144x144.png\">\r\n" + "<link rel=\"apple-touch-icon\" sizes=\"152x152\"\r\n"
			+ "	href=\"res/apple-touch-icon-152x152.png\">\r\n" + "<link rel=\"apple-touch-icon\" sizes=\"180x180\"\r\n"
			+ "	href=\"res/apple-touch-icon-180x180.png\">\r\n"
			+ "<link rel=\"icon\" type=\"image/png\" sizes=\"32x32\"\r\n" + "	href=\"res/favicon-32x32.png\">\r\n"
			+ "<link rel=\"icon\" type=\"image/png\" sizes=\"192x192\"\r\n"
			+ "	href=\"res/android-chrome-192x192.png\">\r\n"
			+ "<link rel=\"icon\" type=\"image/png\" sizes=\"16x16\"\r\n" + "	href=\"res/favicon-16x16.png\">\r\n"
			+ "<link rel=\"manifest\" href=\"res/site.webmanifest\">\r\n"
			+ "<link rel=\"mask-icon\" href=\"res/safari-pinned-tab.svg\" color=\"#5bbad5\">\r\n"
			+ "<link rel=\"shortcut icon\" href=\"res/favicon.ico\">\r\n"
			+ "<meta name=\"msapplication-TileColor\" content=\"#da532c\">\r\n"
			+ "<meta name=\"msapplication-TileImage\" content=\"res/mstile-144x144.png\">\r\n"
			+ "<meta name=\"msapplication-config\" content=\"res/browserconfig.xml\">\r\n"
			+ "<meta name=\"theme-color\" content=\"#ffffff\">\r\n" + "\r\n" + "\r\n"
			+ "<script src=\"https://code.highcharts.com/highcharts.js\"></script>\r\n" + "<script\r\n"
			+ "	src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js\"></script>\r\n"
			+ "	<script src=\"https://code.highcharts.com/modules/exporting.js\"></script>\r\n" + "</head>\r\n"
			+ "<body>\r\n" + "	<div id=\"container\" style=\"width: 100%; height: 700px;\">\r\n"
			+ "		<script>\r\n" + "			$(function() {\r\n"
			+ "				var myChart = Highcharts.chart('container', {\r\n" + "					chart : {\r\n"
			+ "						type : 'line',\r\n" + "						zoomType: 'x'\r\n"
			+ "					},\r\n" + "					title : {\r\n"
			+ "						text : 'Plantie - Infographix'\r\n" + "					},\r\n"
			+ "					subtitle: {\r\n" + "					    text: 'Generated from $amount datasets'\r\n"
			+ "					},\r\n" + "					xAxis : {\r\n"
			+ "						type: 'datetime',\r\n" + "						categories : [ $timeStamp ]\r\n"
			+ "					},\r\n" + "					yAxis : {\r\n" + "						title : {\r\n"
			+ "							text : 'Value'\r\n" + "						}\r\n" + "					},\r\n"
			+ "					series : [ $series ]\r\n" + "				});\r\n" + "			});\r\n"
			+ "		</script>\r\n" + "	</div>\r\n" + "</body>\r\n" + "</html>";

	public static String getHtmltemplate() {
		return htmlTemplate;
	}
}
