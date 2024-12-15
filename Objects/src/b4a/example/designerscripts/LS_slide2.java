package b4a.example.designerscripts;
import anywheresoftware.b4a.objects.TextViewWrapper;
import anywheresoftware.b4a.objects.ImageViewWrapper;
import anywheresoftware.b4a.BA;


public class LS_slide2{

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
views.get("slide_1").vw.setHeight((int)((views.get("slide_1").vw.getWidth())));
views.get("slide_1").vw.setTop((int)((views.get("pnlbackground").vw.getTop() + views.get("pnlbackground").vw.getHeight()) - (views.get("slide_1").vw.getHeight())));
views.get("slide_2").vw.setWidth((int)((60d / 100 * width)));
views.get("slide_2").vw.setHeight((int)((views.get("slide_2").vw.getWidth())));
views.get("slide_2").vw.setTop((int)((40d / 100 * height)));
views.get("slide_2").vw.setLeft((int)(0-(35d / 100 * width)));
views.get("slide_3").vw.setWidth((int)((60d / 100 * width)));
views.get("slide_3").vw.setHeight((int)((views.get("slide_3").vw.getWidth())));
views.get("slide_3").vw.setTop((int)(0-(15d / 100 * height)));
views.get("slide_3").vw.setLeft((int)((views.get("pnlbackground").vw.getWidth())+(20d / 100 * width) - (views.get("slide_3").vw.getWidth())));

}
}