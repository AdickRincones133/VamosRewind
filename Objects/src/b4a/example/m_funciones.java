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
public b4a.example.starter _starter = null;
public b4a.example.rewind _rewind = null;
public static String  _fullscreenactivity(anywheresoftware.b4a.BA _ba,anywheresoftware.b4a.objects.ActivityWrapper _act) throws Exception{
RDebugUtils.currentModule="m_funciones";
if (Debug.shouldDelegate(null, "fullscreenactivity", false))
	 {return ((String) Debug.delegate(null, "fullscreenactivity", new Object[] {_ba,_act}));}
anywheresoftware.b4a.phone.Phone _p = null;
anywheresoftware.b4j.object.JavaObject _jo = null;
anywheresoftware.b4j.object.JavaObject _window = null;
int _bodyheight = 0;
RDebugUtils.currentLine=1966080;
 //BA.debugLineNum = 1966080;BA.debugLine="Sub FullscreenActivity (act As Activity)";
RDebugUtils.currentLine=1966081;
 //BA.debugLineNum = 1966081;BA.debugLine="Dim p As Phone";
_p = new anywheresoftware.b4a.phone.Phone();
RDebugUtils.currentLine=1966082;
 //BA.debugLineNum = 1966082;BA.debugLine="If p.SdkVersion >= 4.4 Then";
if (_p.getSdkVersion()>=4.4) { 
RDebugUtils.currentLine=1966083;
 //BA.debugLineNum = 1966083;BA.debugLine="Dim jo As JavaObject";
_jo = new anywheresoftware.b4j.object.JavaObject();
RDebugUtils.currentLine=1966084;
 //BA.debugLineNum = 1966084;BA.debugLine="Dim window As JavaObject = jo.InitializeContext.";
_window = new anywheresoftware.b4j.object.JavaObject();
_window = (anywheresoftware.b4j.object.JavaObject) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4j.object.JavaObject(), (java.lang.Object)(_jo.InitializeContext((_ba.processBA == null ? _ba : _ba.processBA)).RunMethod("getWindow",(Object[])(anywheresoftware.b4a.keywords.Common.Null))));
RDebugUtils.currentLine=1966085;
 //BA.debugLineNum = 1966085;BA.debugLine="window.RunMethod(\"addFlags\", Array(Bit.Or(0x0000";
_window.RunMethod("addFlags",new Object[]{(Object)(anywheresoftware.b4a.keywords.Common.Bit.Or(((int)0x00000200),((int)0x08000000)))});
RDebugUtils.currentLine=1966088;
 //BA.debugLineNum = 1966088;BA.debugLine="Dim bodyHeight As Int = GetStatusBarHeight + Get";
_bodyheight = (int) (_getstatusbarheight(_ba)+_getnavigationbarheight(_ba));
RDebugUtils.currentLine=1966089;
 //BA.debugLineNum = 1966089;BA.debugLine="act.Height = act.Height + bodyHeight";
_act.setHeight((int) (_act.getHeight()+_bodyheight));
 };
RDebugUtils.currentLine=1966091;
 //BA.debugLineNum = 1966091;BA.debugLine="End Sub";
return "";
}
public static int  _getstatusbarheight(anywheresoftware.b4a.BA _ba) throws Exception{
RDebugUtils.currentModule="m_funciones";
if (Debug.shouldDelegate(null, "getstatusbarheight", false))
	 {return ((Integer) Debug.delegate(null, "getstatusbarheight", new Object[] {_ba}));}
RDebugUtils.currentLine=2031616;
 //BA.debugLineNum = 2031616;BA.debugLine="Sub GetStatusBarHeight As Int";
RDebugUtils.currentLine=2031617;
 //BA.debugLineNum = 2031617;BA.debugLine="Return GetDimension(\"status_bar_height\")";
if (true) return _getdimension(_ba,"status_bar_height");
RDebugUtils.currentLine=2031618;
 //BA.debugLineNum = 2031618;BA.debugLine="End Sub";
return 0;
}
public static int  _getnavigationbarheight(anywheresoftware.b4a.BA _ba) throws Exception{
RDebugUtils.currentModule="m_funciones";
if (Debug.shouldDelegate(null, "getnavigationbarheight", false))
	 {return ((Integer) Debug.delegate(null, "getnavigationbarheight", new Object[] {_ba}));}
RDebugUtils.currentLine=2097152;
 //BA.debugLineNum = 2097152;BA.debugLine="Sub GetNavigationBarHeight As Int";
RDebugUtils.currentLine=2097153;
 //BA.debugLineNum = 2097153;BA.debugLine="Return GetDimension(\"navigation_bar_height\")";
if (true) return _getdimension(_ba,"navigation_bar_height");
RDebugUtils.currentLine=2097154;
 //BA.debugLineNum = 2097154;BA.debugLine="End Sub";
return 0;
}
public static String  _autoescalar(anywheresoftware.b4a.BA _ba,anywheresoftware.b4a.objects.ActivityWrapper _pantalla) throws Exception{
RDebugUtils.currentModule="m_funciones";
if (Debug.shouldDelegate(null, "autoescalar", false))
	 {return ((String) Debug.delegate(null, "autoescalar", new Object[] {_ba,_pantalla}));}
double _fscale = 0;
anywheresoftware.b4a.objects.ConcreteViewWrapper _v = null;
RDebugUtils.currentLine=2686976;
 //BA.debugLineNum = 2686976;BA.debugLine="Public Sub AutoEscalar(Pantalla As Activity)";
RDebugUtils.currentLine=2686977;
 //BA.debugLineNum = 2686977;BA.debugLine="Dim fscale As Double = access.GetUserFontScale";
_fscale = _access.GetUserFontScale();
RDebugUtils.currentLine=2686978;
 //BA.debugLineNum = 2686978;BA.debugLine="If fscale > 1 Then";
if (_fscale>1) { 
RDebugUtils.currentLine=2686979;
 //BA.debugLineNum = 2686979;BA.debugLine="For Each v As View In Pantalla.GetAllViewsRecurs";
_v = new anywheresoftware.b4a.objects.ConcreteViewWrapper();
{
final anywheresoftware.b4a.BA.IterableList group3 = _pantalla.GetAllViewsRecursive();
final int groupLen3 = group3.getSize()
;int index3 = 0;
;
for (; index3 < groupLen3;index3++){
_v = (anywheresoftware.b4a.objects.ConcreteViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.ConcreteViewWrapper(), (android.view.View)(group3.Get(index3)));
RDebugUtils.currentLine=2686980;
 //BA.debugLineNum = 2686980;BA.debugLine="AdjustTextSize(v, fscale)";
_adjusttextsize(_ba,_v,_fscale);
 }
};
 };
RDebugUtils.currentLine=2686983;
 //BA.debugLineNum = 2686983;BA.debugLine="End Sub";
return "";
}
public static String  _adjusttextsize(anywheresoftware.b4a.BA _ba,anywheresoftware.b4a.objects.ConcreteViewWrapper _v,double _fscale) throws Exception{
RDebugUtils.currentModule="m_funciones";
if (Debug.shouldDelegate(null, "adjusttextsize", false))
	 {return ((String) Debug.delegate(null, "adjusttextsize", new Object[] {_ba,_v,_fscale}));}
anywheresoftware.b4a.objects.LabelWrapper _lbl = null;
anywheresoftware.b4a.objects.ButtonWrapper _btn = null;
RDebugUtils.currentLine=2818048;
 //BA.debugLineNum = 2818048;BA.debugLine="Private Sub AdjustTextSize(v As View, fscale As Do";
RDebugUtils.currentLine=2818049;
 //BA.debugLineNum = 2818049;BA.debugLine="If v Is Label Then";
if (_v.getObjectOrNull() instanceof android.widget.TextView) { 
RDebugUtils.currentLine=2818050;
 //BA.debugLineNum = 2818050;BA.debugLine="Dim lbl As Label = v";
_lbl = new anywheresoftware.b4a.objects.LabelWrapper();
_lbl = (anywheresoftware.b4a.objects.LabelWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.LabelWrapper(), (android.widget.TextView)(_v.getObject()));
RDebugUtils.currentLine=2818051;
 //BA.debugLineNum = 2818051;BA.debugLine="lbl.TextSize = Max(10, (lbl.TextSize / fscale) -";
_lbl.setTextSize((float) (anywheresoftware.b4a.keywords.Common.Max(10,(_lbl.getTextSize()/(double)_fscale)-2)));
 }else 
{RDebugUtils.currentLine=2818052;
 //BA.debugLineNum = 2818052;BA.debugLine="Else If v Is Button Then";
if (_v.getObjectOrNull() instanceof android.widget.Button) { 
RDebugUtils.currentLine=2818053;
 //BA.debugLineNum = 2818053;BA.debugLine="Dim btn As Button = v";
_btn = new anywheresoftware.b4a.objects.ButtonWrapper();
_btn = (anywheresoftware.b4a.objects.ButtonWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.ButtonWrapper(), (android.widget.Button)(_v.getObject()));
RDebugUtils.currentLine=2818054;
 //BA.debugLineNum = 2818054;BA.debugLine="btn.TextSize = Max(10, btn.TextSize / fscale) '";
_btn.setTextSize((float) (anywheresoftware.b4a.keywords.Common.Max(10,_btn.getTextSize()/(double)_fscale)));
 }}
;
RDebugUtils.currentLine=2818056;
 //BA.debugLineNum = 2818056;BA.debugLine="End Sub";
return "";
}
public static int  _getdimension(anywheresoftware.b4a.BA _ba,String _resourcename) throws Exception{
RDebugUtils.currentModule="m_funciones";
if (Debug.shouldDelegate(null, "getdimension", false))
	 {return ((Integer) Debug.delegate(null, "getdimension", new Object[] {_ba,_resourcename}));}
anywheresoftware.b4j.object.JavaObject _context = null;
anywheresoftware.b4j.object.JavaObject _res = null;
int _resourceid = 0;
RDebugUtils.currentLine=2752512;
 //BA.debugLineNum = 2752512;BA.debugLine="Private Sub GetDimension(resourceName As String) A";
RDebugUtils.currentLine=2752513;
 //BA.debugLineNum = 2752513;BA.debugLine="Dim context As JavaObject";
_context = new anywheresoftware.b4j.object.JavaObject();
RDebugUtils.currentLine=2752514;
 //BA.debugLineNum = 2752514;BA.debugLine="context.InitializeContext";
_context.InitializeContext((_ba.processBA == null ? _ba : _ba.processBA));
RDebugUtils.currentLine=2752515;
 //BA.debugLineNum = 2752515;BA.debugLine="Dim res As JavaObject = context.RunMethod(\"getRes";
_res = new anywheresoftware.b4j.object.JavaObject();
_res = (anywheresoftware.b4j.object.JavaObject) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4j.object.JavaObject(), (java.lang.Object)(_context.RunMethod("getResources",(Object[])(anywheresoftware.b4a.keywords.Common.Null))));
RDebugUtils.currentLine=2752516;
 //BA.debugLineNum = 2752516;BA.debugLine="Dim resourceId As Int = res.RunMethod(\"getIdentif";
_resourceid = (int)(BA.ObjectToNumber(_res.RunMethod("getIdentifier",new Object[]{(Object)(_resourcename),(Object)("dimen"),(Object)("android")})));
RDebugUtils.currentLine=2752517;
 //BA.debugLineNum = 2752517;BA.debugLine="If resourceId > 0 Then";
if (_resourceid>0) { 
RDebugUtils.currentLine=2752518;
 //BA.debugLineNum = 2752518;BA.debugLine="Return res.RunMethod(\"getDimensionPixelSize\", Ar";
if (true) return (int)(BA.ObjectToNumber(_res.RunMethod("getDimensionPixelSize",new Object[]{(Object)(_resourceid)})));
 }else {
RDebugUtils.currentLine=2752520;
 //BA.debugLineNum = 2752520;BA.debugLine="Return 0";
if (true) return (int) (0);
 };
RDebugUtils.currentLine=2752522;
 //BA.debugLineNum = 2752522;BA.debugLine="End Sub";
return 0;
}
}