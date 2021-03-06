package pe1.uf5.m3.dam2;

import java.io.IOException;
import java.net.MalformedURLException;

import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.io.font.FontConstants;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.events.Event;
import com.itextpdf.kernel.events.IEventHandler;
import com.itextpdf.kernel.events.PdfDocumentEvent;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfNumber;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.layout.Canvas;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.property.TextAlignment;

public class PdfManager implements IEventHandler {
    public static final float PAGECOUNT_BOXSIDE = 20.0f;
    public static final String HEADER_TEXT = "Generalitat de Catalunya\nDepartament d’Ensenyament\nINS Marianao";
    public static final String HEADER_IMAGE = "resources/logo-gene.jpg";
    public static final String FONT_TRADEGOTHIC = "resources/TradeGothic.woff";
    public static final String FONT_FREEHAND = "resources/Freehand591BT-RegularA.woff";
    public static final String FONT_MAXIME = "resources/MaximeStd-Regular.woff";

    public static final float MARGIN_LEFT = 50.0f;
    public static final float MARGIN_RIGHT = 50.0f;
    public static final float MARGIN_TOP = 80.0f;
    public static final float MARGIN_BOTTOM = 60.0f;

    public static final float LOGO_WIDTH = 25.0f;
    public static final float LOGO_HEIGHT = 29.0f;

    public static final PdfNumber LANDSCAPE = new PdfNumber(90);
    public static final PdfNumber PORTRAIT = new PdfNumber(0);

    private String outputPdfFile;

    private PdfDocument pdfDocument;
    private PdfFont headerFont;
    // private PdfFont bodyFont;

    public PdfManager(String outputPdfFile) throws Exception {
        this.outputPdfFile = outputPdfFile;

        try {
            this.headerFont = PdfFontFactory.createFont(FontConstants.HELVETICA);

            // PdfFontFactory.createFont(this.getClass().getResource("resources/TradeGothic.ttf").getFile(),
            // PdfEncodings.IDENTITY_H); // Escriptura horitzontal
            // this.bodyFont =
            // PdfFontFactory.createFont(this.getClass().getResource("resources/MaximeStd-Regular.ttf").getFile(),
            // PdfEncodings.IDENTITY_H);
            // this.bodyFont =
            // PdfFontFactory.createFont(this.getClass().getResource("resources/Freehand591BT-RegularA.ttf").getFile(),
            // PdfEncodings.IDENTITY_H);
        } catch (IOException e) {
            // e.printStackTrace();
            throw new Exception("Error creant el tipus de font del document. " + e.getMessage());
        }
    }

    public void generarPDF(String titol, String htmlContent, boolean landscape) throws Exception {
        // Create Document
        PdfWriter writer = null;

        try {
            writer = new PdfWriter(outputPdfFile);
            this.pdfDocument = new PdfDocument(writer);

            String htmlPre = "";
            htmlPre += "<!DOCTYPE html>";
            htmlPre += "<html lang='ca'>";
            htmlPre += "	<head>";
            htmlPre += "		<meta charset='UTF-8'>";
            htmlPre += "		<title>" + titol + "</title>";
            htmlPre += "		<style type='text/css'>";
            htmlPre += "		@font-face {";
            htmlPre += "		    font-family: 'TradeGothic';";
            htmlPre += "		    src: url('" + FONT_TRADEGOTHIC + "') format('woff');";
            htmlPre += "		}";
            htmlPre += "		@font-face {";
            htmlPre += "		    font-family: 'Freehand-Regular';";
            htmlPre += "		    src: url('" + FONT_FREEHAND + "') format('woff');";
            htmlPre += "		}";
            htmlPre += "		@font-face {";
            htmlPre += "		    font-family: 'MaximeStd-Regular';";
            htmlPre += "		    src: url('" + FONT_MAXIME + "') format('woff');";
            htmlPre += "		}";
            htmlPre += "		* {";
            htmlPre += "		    font-family: 'FreeSans';";  // 'FreeSerif' o 'FreeMono'
            htmlPre += "		    font-size: 11pt;";
            htmlPre += "		}";
            htmlPre += "		@page{";
            htmlPre += " 			margin: " + MARGIN_TOP + "pt " + MARGIN_RIGHT + "pt " + MARGIN_BOTTOM + "pt " + MARGIN_LEFT + "pt;";
            htmlPre += " 			@bottom-center {";
            htmlPre += " 			    content: 'Pàgina ' counter(page) ' de ' counter(pages);";
            htmlPre += "		    	font-size: 9pt;";
            htmlPre += " 			}";
            htmlPre += "		}";
            htmlPre += "		h1, h2, h3 {";
            htmlPre += "		    font-family: 'TradeGothic';";
            htmlPre += "		}";
            htmlPre += "		h1 {";
            htmlPre += "		    font-size: 20pt;";
            htmlPre += "		}";
            htmlPre += "		h2 {";
            htmlPre += "		    font-size: 16pt;";
            htmlPre += "		}";
            htmlPre += "		h3 {";
            htmlPre += "		    font-size: 14pt;";
            htmlPre += "		    margin: 30pt 0 15pt;";
            htmlPre += "		}";
            htmlPre += "		.open-response {";
            htmlPre += "		    border: 1px solid black;";
            htmlPre += "		    padding: 60pt 0;";
            htmlPre += "		}";
            htmlPre += "		ul {";
            htmlPre += "		    list-style: none;";
            htmlPre += "		    padding: 0;";
            htmlPre += "		    margin: 0;";
            htmlPre += "		}";
            htmlPre += "		li {";
            htmlPre += "		    padding-left: 20pt;";
            htmlPre += "		    line-height: 0.8;";
            htmlPre += "		}";
            htmlPre += "		li:before {";
            htmlPre += "		    content: '\u25A2';";
            htmlPre += "		    padding-right: 5pt;";
            htmlPre += "		    padding-top: 1pt;";
            htmlPre += "		    font-size: 20pt;";
            htmlPre += "		}";
            htmlPre += "		ul.truefalse-response {";
            htmlPre += "		    text-align: right;";
            htmlPre += "		}";
            htmlPre += "		ul.truefalse-response li {";
            htmlPre += "		    display: inline;";
            htmlPre += "		    padding-left: 5pt;";
            htmlPre += "		    padding-right: 20pt;";
            htmlPre += "		}";
            htmlPre += "		table { ";
            htmlPre += "			width: 100% ";
            htmlPre += "		}";
            htmlPre += "		tr > td { ";
            htmlPre += "			border-color: #778899; font-size: 9pt; padding: 10pt 4pt; ";
            htmlPre += "		}";
            htmlPre += "		tr.header-row > td { ";
            htmlPre += "			background-color: #778899; color: white; font-family: 'TradeGothic'; font-size: 14pt; font-weight: bold; padding: 10pt 6pt; line-height: 1.1em; ";
            htmlPre += "		}";
            htmlPre += "		tr.subheader-row > td { ";
            htmlPre += "			background-color: #e6e6fa; color: #778899; font-size: 0.9em; padding: 4pt;";
            htmlPre += "		}";
            htmlPre += "		div.container { ";
            // htmlPre += " display: block;";
            htmlPre += "			color: #778899;";
            htmlPre += "			width: 90%;";
            htmlPre += "			text-align: center;";
            htmlPre += "			border: 1pt double #778899;";
            htmlPre += "			margin-top: 5pt;";
            htmlPre += "			margin-left: auto;";
            htmlPre += "			margin-right: auto;";
            htmlPre += "			line-height: 1.1em;";
            htmlPre += "			padding: 5pt;";
            // htmlPre += " -webkit-box-shadow: 10px -10px 7px 0px
            // rgba(119,136,153,1);";
            // htmlPre += " -moz-box-shadow: 10px -10px 7px 0px
            // rgba(119,136,153,1);";
            // htmlPre += " box-shadow: 10px -10px 7px 0px
            // rgba(119,136,153,1);";
            htmlPre += "		}";
            htmlPre += "		div.container-empty { ";
            // htmlPre += " display: block;";
            htmlPre += "			height: 38pt;";
            htmlPre += "			border: 1pt dashed #778899;";
            htmlPre += "		}";
            htmlPre += "		span.ubicacio-label { ";
            htmlPre += "			padding: 5pt;";
            htmlPre += "			color: #c14c41;";
            htmlPre += "			background-color: #f2dcb3;";
            htmlPre += "			border: 1pt solid #f2a994;";
            htmlPre += "			border-radius: 2pt;";
            htmlPre += "			position: absolute;";
            htmlPre += "			top: 0;";
            htmlPre += "			left: 5pt;";
            htmlPre += "		}";
            htmlPre += "		span.container-item { ";
            // htmlPre += " display: block;";
            // htmlPre += " width: 200pt;";container-header
            // htmlPre += " text-align: center;";
            htmlPre += "			line-height: 1.2em;";
            htmlPre += "		}";
            htmlPre += "		.container-item.container-header { ";
            // htmlPre += " margin-top: 25pt;";
            htmlPre += "			font-weight: bold;";
            htmlPre += "			font-size: 12pt;";
            htmlPre += "		}";
            htmlPre += "		.container-item.container-data { ";
            htmlPre += "			font-size: 0.8em;";
            htmlPre += "		}";
            htmlPre += "		.red {";
            htmlPre += "		    color: red;";
            htmlPre += "		}";
            htmlPre += "		.orange {";
            htmlPre += "		    color: orange;";
            htmlPre += "		}";
            htmlPre += "		.green {";
            htmlPre += "		    color: green;";
            htmlPre += "		}";
            htmlPre += "		</style>";
            htmlPre += "	</head>";
            htmlPre += "	<body>";

            String htmlPost = "";
            htmlPost += "	</body>";
            htmlPost += "</html>";

            // Assign event-handlers
            this.pdfDocument.addEventHandler(PdfDocumentEvent.START_PAGE, this);	// Després de crear la pàgina
            if (landscape) {
                this.pdfDocument.setDefaultPageSize(PageSize.A4.rotate());
            }

            ConverterProperties converterProperties = new ConverterProperties();
            Document document = HtmlConverter.convertToDocument(htmlPre + htmlContent + htmlPost, this.pdfDocument, converterProperties);

            document.close();
            this.pdfDocument.close();
        } catch (IOException e) {
            // e.printStackTrace();
            throw new Exception("Error d'entrada/sortida. " + e.getMessage());
        }
    }

    @Override
    public void handleEvent(Event event) {
        PdfDocumentEvent docEvent = (PdfDocumentEvent) event;

        PdfDocument pdf = docEvent.getDocument();

        PdfPage page = docEvent.getPage();

        Rectangle pageSize = page.getPageSize();
        PdfCanvas pdfCanvas = new PdfCanvas(page.getLastContentStream(), page.getResources(), pdf);
        // PdfCanvas pdfCanvas = new PdfCanvas(page.newContentStreamBefore(), page.getResources(), pdf);
        Canvas canvas = new Canvas(pdfCanvas, pdf, pageSize);

        float headerY = pageSize.getTop() - 25 - LOGO_HEIGHT;
        Image logo = null;
        try {
            // logo = new Image(ImageDataFactory.create(this.getClass().getResource(HEADER_IMAGE).getFile()));
            logo = new Image(ImageDataFactory.create(HEADER_IMAGE));
            logo.scaleToFit(LOGO_WIDTH, LOGO_HEIGHT);	// absolute size
            logo.setFixedPosition(MARGIN_LEFT - LOGO_WIDTH - 3, headerY);

            canvas.add(logo);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        Paragraph p = new Paragraph(HEADER_TEXT).setFont(headerFont).setFontSize(9f).setMultipliedLeading(1); // Line height 1
        canvas.showTextAligned(p, MARGIN_LEFT, headerY, TextAlignment.LEFT);

        pdfCanvas.release();
        canvas.close();
    }
}
