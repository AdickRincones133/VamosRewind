package b4a.example.designerscripts;
import anywheresoftware.b4a.objects.TextViewWrapper;
import anywheresoftware.b4a.objects.ImageViewWrapper;
import anywheresoftware.b4a.BA;


public class LS_slide1{

public static void LS_general(anywheresoftware.b4a.BA ba, android.view.View parent, anywheresoftware.b4a.keywords.LayoutValues lv, java.util.Map props,
java.util.Map<String, anywheresoftware.b4a.keywords.LayoutBuilder.ViewWrapperAndAnchor> views, int width, int height, float scale) throws Exception {
anywheresoftware.b4a.keywords.LayoutBuilder.setScaleRate(0.3);
anywheresoftware.b4a.keywords.LayoutBuilder.scaleAll(views);
views.get("pnlbackground").vw.setLeft((int)(0d));
views.get("pnlbackground").vw.setWidth((int)((100d / 100 * width) - (0d)));
views.get("pnlbackground").vw.setTop((int)(0d));
views.get("pnlbackground").vw.setHeight((int)((100d / 100 * height) - (0d)));
views.get("slide_1").vw.setLeft((int)(0d));
views.get("slide_1").vw.setWidth((int)((views.get("pnlbackground").vw.getWidth()) - (0d)));
//BA.debugLineNum = 8;BA.debugLine="slide_1.Height = slide_1.Width"[slide1/General script]
views.get("slide_1").vw.setHeight((int)((views.get("slide_1").vw.getWidth())));
//BA.debugLineNum = 9;BA.debugLine="slide_1.Bottom = pnlBackground.Bottom"[slide1/General script]
views.get("slide_1").vw.setTop((int)((views.get("pnlbackground").vw.getTop() + views.get("pnlbackground").vw.getHeight()) - (views.get("slide_1").vw.getHeight())));
//BA.debugLineNum = 11;BA.debugLine="slide_2.Width = 15%x"[slide1/General script]
views.get("slide_2").vw.setWidth((int)((15d / 100 * width)));
//BA.debugLineNum = 12;BA.debugLine="slide_2.Height = slide_2.Width"[slide1/General script]
views.get("slide_2").vw.setHeight((int)((views.get("slide_2").vw.getWidth())));
//BA.debugLineNum = 13;BA.debugLine="slide_2.Top = 50%y"[slide1/General script]
views.get("slide_2").vw.setTop((int)((50d / 100 * height)));
//BA.debugLineNum = 14;BA.debugLine="slide_2.Left = 10%x"[slide1/General script]
views.get("slide_2").vw.setLeft((int)((10d / 100 * width)));
//BA.debugLineNum = 16;BA.debugLine="slide_3.Width = 15%x"[slide1/General script]
views.get("slide_3").vw.setWidth((int)((15d / 100 * width)));
//BA.debugLineNum = 17;BA.debugLine="slide_3.Height = slide_3.Width"[slide1/General script]
views.get("slide_3").vw.setHeight((int)((views.get("slide_3").vw.getWidth())));
//BA.debugLineNum = 18;BA.debugLine="slide_3.Top = 5%y"[slide1/General script]
views.get("slide_3").vw.setTop((int)((5d / 100 * height)));
//BA.debugLineNum = 19;BA.debugLine="slide_3.Right = pnlBackground.Width - 10%x"[slide1/General script]
views.get("slide_3").vw.setLeft((int)((views.get("pnlbackground").vw.getWidth())-(10d / 100 * width) - (views.get("slide_3").vw.getWidth())));
//BA.debugLineNum = 21;BA.debugLine="slide_4.SetLeftAndRight(0, pnlBackground.Width)"[slide1/General script]
views.get("slide_4").vw.setLeft((int)(0d));
views.get("slide_4").vw.setWidth((int)((views.get("pnlbackground").vw.getWidth()) - (0d)));
//BA.debugLineNum = 22;BA.debugLine="slide_4.Height = slide_4.Width"[slide1/General script]
views.get("slide_4").vw.setHeight((int)((views.get("slide_4").vw.getWidth())));
//BA.debugLineNum = 23;BA.debugLine="slide_4.Top = 10%y"[slide1/General script]
views.get("slide_4").vw.setTop((int)((10d / 100 * height)));

}
}