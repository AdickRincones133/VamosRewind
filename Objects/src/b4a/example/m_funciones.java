package b4a.example;


import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.BALayout;
import anywheresoftware.b4a.debug.*;

public class m_funciones {
private static m_funciones mostCurrent = new m_funciones();
public static Object getObject() {
    throw new RuntimeException("Code module does not support this method.");
}
 public anywheresoftware.b4a.keywords.Common __c = null;
public static anywheresoftware.b4a.objects.Accessibility.Accessibility2 _access = null;
public b4a.example.main _main = null;
public b4a.example.rewind _rewind = null;
public b4a.example.starter _starter = null;
public b4a.example.httputils2service _httputils2service = null;
public static String  _adjusttextsize(anywheresoftware.b4a.BA _ba,anywheresoftware.b4a.objects.ConcreteViewWrapper _v,double _fscale) throws Exception{
anywheresoftware.b4a.objects.LabelWrapper _lbl = null;
anywheresoftware.b4a.objects.ButtonWrapper _btn = null;
 //BA.debugLineNum = 56;BA.debugLine="Private Sub AdjustTextSize(v As View, fscale As Do";
 //BA.debugLineNum = 57;BA.debugLine="If v Is Label Then";
if (_v.getObjectOrNull() instanceof android.widget.TextView) { 
 //BA.debugLineNum = 58;BA.debugLine="Dim lbl As Label = v";
_lbl = new anywheresoftware.b4a.objects.LabelWrapper();
_lbl = (anywheresoftware.b4a.objects.LabelWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.LabelWrapper(), (android.widget.TextView)(_v.getObject()));
 //BA.debugLineNum = 59;BA.debugLine="lbl.TextSize = Max(10, (lbl.TextSize / fscale) -";
_lbl.setTextSize((float) (anywheresoftware.b4a.keywords.Common.Max(10,(_lbl.getTextSize()/(double)_fscale)-2)));
 }else if(_v.getObjectOrNull() instanceof android.widget.Button) { 
 //BA.debugLineNum = 61;BA.debugLine="Dim btn As Button = v";
_btn = new anywheresoftware.b4a.objects.ButtonWrapper();
_btn = (anywheresoftware.b4a.objects.ButtonWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.ButtonWrapper(), (android.widget.Button)(_v.getObject()));
 //BA.debugLineNum = 62;BA.debugLine="btn.TextSize = Max(10, btn.TextSize / fscale) '";
_btn.setTextSize((float) (anywheresoftware.b4a.keywords.Common.Max(10,_btn.getTextSize()/(double)_fscale)));
 };
 //BA.debugLineNum = 64;BA.debugLine="End Sub";
return "";
}
public static String  _autoescalar(anywheresoftware.b4a.BA _ba,anywheresoftware.b4a.objects.ActivityWrapper _pantalla) throws Exception{
double _fscale = 0;
anywheresoftware.b4a.objects.ConcreteViewWrapper _v = null;
 //BA.debugLineNum = 46;BA.debugLine="Public Sub AutoEscalar(Pantalla As Activity)";
 //BA.debugLineNum = 47;BA.debugLine="Dim fscale As Double = access.GetUserFontScale";
_fscale = _access.GetUserFontScale();
 //BA.debugLineNum = 48;BA.debugLine="If fscale > 1 Then";
if (_fscale>1) { 
 //BA.debugLineNum = 49;BA.debugLine="For Each v As View In Pantalla.GetAllViewsRecurs";
_v = new anywheresoftware.b4a.objects.ConcreteViewWrapper();
{
final anywheresoftware.b4a.BA.IterableList group3 = _pantalla.GetAllViewsRecursive();
final int groupLen3 = group3.getSize()
;int index3 = 0;
;
for (; index3 < groupLen3;index3++){
_v = (anywheresoftware.b4a.objects.ConcreteViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.ConcreteViewWrapper(), (android.view.View)(group3.Get(index3)));
 //BA.debugLineNum = 50;BA.debugLine="AdjustTextSize(v, fscale)";
_adjusttextsize(_ba,_v,_fscale);
 }
};
 };
 //BA.debugLineNum = 53;BA.debugLine="End Sub";
return "";
}
public static String  _fullscreenactivity(anywheresoftware.b4a.BA _ba,anywheresoftware.b4a.objects.ActivityWrapper _act) throws Exception{
anywheresoftware.b4a.phone.Phone _p = null;
anywheresoftware.b4j.object.JavaObject _jo = null;
anywheresoftware.b4j.object.JavaObject _window = null;
int _bodyheight = 0;
 //BA.debugLineNum = 9;BA.debugLine="Sub FullscreenActivity (act As Activity)";
 //BA.debugLineNum = 10;BA.debugLine="Dim p As Phone";
_p = new anywheresoftware.b4a.phone.Phone();
 //BA.debugLineNum = 11;BA.debugLine="If p.SdkVersion >= 4.4 Then";
if (_p.getSdkVersion()>=4.4) { 
 //BA.debugLineNum = 12;BA.debugLine="Dim jo As JavaObject";
_jo = new anywheresoftware.b4j.object.JavaObject();
 //BA.debugLineNum = 13;BA.debugLine="Dim window As JavaObject = jo.InitializeContext.";
_window = new anywheresoftware.b4j.object.JavaObject();
_window = (anywheresoftware.b4j.object.JavaObject) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4j.object.JavaObject(), (java.lang.Object)(_jo.InitializeContext((_ba.processBA == null ? _ba : _ba.processBA)).RunMethod("getWindow",(Object[])(anywheresoftware.b4a.keywords.Common.Null))));
 //BA.debugLineNum = 14;BA.debugLine="window.RunMethod(\"addFlags\", Array(Bit.Or(0x0000";
_window.RunMethod("addFlags",new Object[]{(Object)(anywheresoftware.b4a.keywords.Common.Bit.Or(((int)0x00000200),((int)0x08000000)))});
 //BA.debugLineNum = 17;BA.debugLine="Dim bodyHeight As Int = GetStatusBarHeight + Get";
_bodyheight = (int) (_getstatusbarheight(_ba)+_getnavigationbarheight(_ba));
 //BA.debugLineNum = 18;BA.debugLine="act.Height = act.Height + bodyHeight";
_act.setHeight((int) (_act.getHeight()+_bodyheight));
 };
 //BA.debugLineNum = 20;BA.debugLine="End Sub";
return "";
}
public static int  _getdimension(anywheresoftware.b4a.BA _ba,String _resourcename) throws Exception{
anywheresoftware.b4j.object.JavaObject _context = null;
anywheresoftware.b4j.object.JavaObject _res = null;
int _resourceid = 0;
 //BA.debugLineNum = 33;BA.debugLine="Private Sub GetDimension(resourceName As String) A";
 //BA.debugLineNum = 34;BA.debugLine="Dim context As JavaObject";
_context = new anywheresoftware.b4j.object.JavaObject();
 //BA.debugLineNum = 35;BA.debugLine="context.InitializeContext";
_context.InitializeContext((_ba.processBA == null ? _ba : _ba.processBA));
 //BA.debugLineNum = 36;BA.debugLine="Dim res As JavaObject = context.RunMethod(\"getRes";
_res = new anywheresoftware.b4j.object.JavaObject();
_res = (anywheresoftware.b4j.object.JavaObject) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4j.object.JavaObject(), (java.lang.Object)(_context.RunMethod("getResources",(Object[])(anywheresoftware.b4a.keywords.Common.Null))));
 //BA.debugLineNum = 37;BA.debugLine="Dim resourceId As Int = res.RunMethod(\"getIdentif";
_resourceid = (int)(BA.ObjectToNumber(_res.RunMethod("getIdentifier",new Object[]{(Object)(_resourcename),(Object)("dimen"),(Object)("android")})));
 //BA.debugLineNum = 38;BA.debugLine="If resourceId > 0 Then";
if (_resourceid>0) { 
 //BA.debugLineNum = 39;BA.debugLine="Return res.RunMethod(\"getDimensionPixelSize\", Ar";
if (true) return (int)(BA.ObjectToNumber(_res.RunMethod("getDimensionPixelSize",new Object[]{(Object)(_resourceid)})));
 }else {
 //BA.debugLineNum = 41;BA.debugLine="Return 0";
if (true) return (int) (0);
 };
 //BA.debugLineNum = 43;BA.debugLine="End Sub";
return 0;
}
public static int  _getnavigationbarheight(anywheresoftware.b4a.BA _ba) throws Exception{
 //BA.debugLineNum = 28;BA.debugLine="Sub GetNavigationBarHeight As Int";
 //BA.debugLineNum = 29;BA.debugLine="Return GetDimension(\"navigation_bar_height\")";
if (true) return _getdimension(_ba,"navigation_bar_height");
 //BA.debugLineNum = 30;BA.debugLine="End Sub";
return 0;
}
public static int  _getstatusbarheight(anywheresoftware.b4a.BA _ba) throws Exception{
 //BA.debugLineNum = 23;BA.debugLine="Sub GetStatusBarHeight As Int";
 //BA.debugLineNum = 24;BA.debugLine="Return GetDimension(\"status_bar_height\")";
if (true) return _getdimension(_ba,"status_bar_height");
 //BA.debugLineNum = 25;BA.debugLine="End Sub";
return 0;
}
public static String  _process_globals() throws Exception{
 //BA.debugLineNum = 3;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 6;BA.debugLine="Private access As Accessiblity";
_access = new anywheresoftware.b4a.objects.Accessibility.Accessibility2();
 //BA.debugLineNum = 7;BA.debugLine="End Sub";
return "";
}
}
