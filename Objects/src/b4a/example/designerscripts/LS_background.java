package b4a.example.designerscripts;
import anywheresoftware.b4a.objects.TextViewWrapper;
import anywheresoftware.b4a.objects.ImageViewWrapper;
import anywheresoftware.b4a.BA;


public class LS_background{

public static void LS_general(anywheresoftware.b4a.BA ba, android.view.View parent, anywheresoftware.b4a.keywords.LayoutValues lv, java.util.Map props,
java.util.Map<String, anywheresoftware.b4a.keywords.LayoutBuilder.ViewWrapperAndAnchor> views, int width, int height, float scale) throws Exception {
anywheresoftware.b4a.keywords.LayoutBuilder.setScaleRate(0.3);
//BA.debugLineNum = 2;BA.debugLine="AutoScaleAll"[Background/General script]
anywheresoftware.b4a.keywords.LayoutBuilder.scaleAll(views);
//BA.debugLineNum = 4;BA.debugLine="pnlBackground.SetLeftAndRight(0, 100%x)"[Background/General script]
views.get("pnlbackground").vw.setLeft((int)(0d));
views.get("pnlbackground").vw.setWidth((int)((100d / 100 * width) - (0d)));
//BA.debugLineNum = 5;BA.debugLine="pnlBackground.SetTopAndBottom(0, 100%y)"[Background/General script]
views.get("pnlbackground").vw.setTop((int)(0d));
views.get("pnlbackground").vw.setHeight((int)((100d / 100 * height) - (0d)));
//BA.debugLineNum = 7;BA.debugLine="pnlContent.SetLeftAndRight(0, pnlBackground.Width)"[Background/General script]
views.get("pnlcontent").vw.setLeft((int)(0d));
views.get("pnlcontent").vw.setWidth((int)((views.get("pnlbackground").vw.getWidth()) - (0d)));
//BA.debugLineNum = 8;BA.debugLine="pnlContent.SetTopAndBottom(0, pnlBackground.Height)"[Background/General script]
views.get("pnlcontent").vw.setTop((int)(0d));
views.get("pnlcontent").vw.setHeight((int)((views.get("pnlbackground").vw.getHeight()) - (0d)));

}
}