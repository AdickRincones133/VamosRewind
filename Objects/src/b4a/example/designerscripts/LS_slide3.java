package b4a.example.designerscripts;
import anywheresoftware.b4a.objects.TextViewWrapper;
import anywheresoftware.b4a.objects.ImageViewWrapper;
import anywheresoftware.b4a.BA;


public class LS_slide3{

public static void LS_general(anywheresoftware.b4a.BA ba, android.view.View parent, anywheresoftware.b4a.keywords.LayoutValues lv, java.util.Map props,
java.util.Map<String, anywheresoftware.b4a.keywords.LayoutBuilder.ViewWrapperAndAnchor> views, int width, int height, float scale) throws Exception {
anywheresoftware.b4a.keywords.LayoutBuilder.setScaleRate(0.3);
String _lightning_1="";
String _lightning_2="";
anywheresoftware.b4a.keywords.LayoutBuilder.scaleAll(views);
views.get("pnlbackground").vw.setLeft((int)(0d));
views.get("pnlbackground").vw.setWidth((int)((100d / 100 * width) - (0d)));
views.get("pnlbackground").vw.setTop((int)(0d));
views.get("pnlbackground").vw.setHeight((int)((100d / 100 * height) - (0d)));
views.get("slide_1").vw.setLeft((int)(0d));
views.get("slide_1").vw.setWidth((int)((views.get("pnlbackground").vw.getWidth()) - (0d)));
views.get("slide_1").vw.setHeight((int)((views.get("slide_1").vw.getWidth())));
views.get("slide_1").vw.setTop((int)((views.get("pnlbackground").vw.getTop() + views.get("pnlbackground").vw.getHeight()) - (views.get("slide_1").vw.getHeight())));
_lightning_1 = BA.NumberToString((40d / 100 * width));
_lightning_2 = BA.NumberToString((15d / 100 * width));
views.get("slide_2").vw.setWidth((int)(Double.parseDouble(_lightning_1)));
views.get("slide_2").vw.setHeight((int)((views.get("slide_2").vw.getWidth())));
views.get("slide_2").vw.setTop((int)((50d / 100 * height)));
views.get("slide_2").vw.setLeft((int)((40d / 100 * width)));
views.get("slide_3").vw.setWidth((int)(Double.parseDouble(_lightning_2)));
views.get("slide_3").vw.setHeight((int)((views.get("slide_3").vw.getWidth())));
views.get("slide_3").vw.setTop((int)((50d / 100 * height)));
views.get("slide_3").vw.setLeft((int)((70d / 100 * width)));
//BA.debugLineNum = 24;BA.debugLine="slide_4.Width = lightning_1"[slide3/General script]
views.get("slide_4").vw.setWidth((int)(Double.parseDouble(_lightning_1)));
//BA.debugLineNum = 25;BA.debugLine="slide_4.Height = slide_4.Width"[slide3/General script]
views.get("slide_4").vw.setHeight((int)((views.get("slide_4").vw.getWidth())));
//BA.debugLineNum = 26;BA.debugLine="slide_4.Top = 60%y"[slide3/General script]
views.get("slide_4").vw.setTop((int)((60d / 100 * height)));
//BA.debugLineNum = 27;BA.debugLine="slide_4.Right = pnlBackground.Width + 10%x"[slide3/General script]
views.get("slide_4").vw.setLeft((int)((views.get("pnlbackground").vw.getWidth())+(10d / 100 * width) - (views.get("slide_4").vw.getWidth())));
//BA.debugLineNum = 29;BA.debugLine="slide_5.Width = lightning_2"[slide3/General script]
views.get("slide_5").vw.setWidth((int)(Double.parseDouble(_lightning_2)));
//BA.debugLineNum = 30;BA.debugLine="slide_5.Height = slide_5.Width"[slide3/General script]
views.get("slide_5").vw.setHeight((int)((views.get("slide_5").vw.getWidth())));
//BA.debugLineNum = 31;BA.debugLine="slide_5.Top = 80%y"[slide3/General script]
views.get("slide_5").vw.setTop((int)((80d / 100 * height)));
//BA.debugLineNum = 32;BA.debugLine="slide_5.Right = pnlBackground.Width - 20%x"[slide3/General script]
views.get("slide_5").vw.setLeft((int)((views.get("pnlbackground").vw.getWidth())-(20d / 100 * width) - (views.get("slide_5").vw.getWidth())));
//BA.debugLineNum = 34;BA.debugLine="slide_6.Width = lightning_1"[slide3/General script]
views.get("slide_6").vw.setWidth((int)(Double.parseDouble(_lightning_1)));
//BA.debugLineNum = 35;BA.debugLine="slide_6.Height = slide_6.Width"[slide3/General script]
views.get("slide_6").vw.setHeight((int)((views.get("slide_6").vw.getWidth())));
//BA.debugLineNum = 36;BA.debugLine="slide_6.Top = 80%y"[slide3/General script]
views.get("slide_6").vw.setTop((int)((80d / 100 * height)));
//BA.debugLineNum = 37;BA.debugLine="slide_6.Right = pnlBackground.Width + 5%x"[slide3/General script]
views.get("slide_6").vw.setLeft((int)((views.get("pnlbackground").vw.getWidth())+(5d / 100 * width) - (views.get("slide_6").vw.getWidth())));
//BA.debugLineNum = 39;BA.debugLine="slide_7.Width = lightning_2"[slide3/General script]
views.get("slide_7").vw.setWidth((int)(Double.parseDouble(_lightning_2)));
//BA.debugLineNum = 40;BA.debugLine="slide_7.Height = slide_7.Width"[slide3/General script]
views.get("slide_7").vw.setHeight((int)((views.get("slide_7").vw.getWidth())));
//BA.debugLineNum = 41;BA.debugLine="slide_7.Top = 90%y"[slide3/General script]
views.get("slide_7").vw.setTop((int)((90d / 100 * height)));
//BA.debugLineNum = 42;BA.debugLine="slide_7.Right = pnlBackground.Width - 30%x"[slide3/General script]
views.get("slide_7").vw.setLeft((int)((views.get("pnlbackground").vw.getWidth())-(30d / 100 * width) - (views.get("slide_7").vw.getWidth())));

}
}