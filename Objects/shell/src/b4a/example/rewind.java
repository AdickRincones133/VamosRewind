
package b4a.example;

import java.io.IOException;
import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.pc.PCBA;
import anywheresoftware.b4a.pc.RDebug;
import anywheresoftware.b4a.pc.RemoteObject;
import anywheresoftware.b4a.pc.RDebug.IRemote;
import anywheresoftware.b4a.pc.Debug;
import anywheresoftware.b4a.pc.B4XTypes.B4XClass;
import anywheresoftware.b4a.pc.B4XTypes.DeviceClass;

public class rewind implements IRemote{
	public static rewind mostCurrent;
	public static RemoteObject processBA;
    public static boolean processGlobalsRun;
    public static RemoteObject myClass;
    public static RemoteObject remoteMe;
	public rewind() {
		mostCurrent = this;
	}
    public RemoteObject getRemoteMe() {
        return remoteMe;    
    }
    
	public static void main (String[] args) throws Exception {
		new RDebug(args[0], Integer.parseInt(args[1]), Integer.parseInt(args[2]), args[3]);
		RDebug.INSTANCE.waitForTask();

	}
    static {
        anywheresoftware.b4a.pc.RapidSub.moduleToObject.put(new B4XClass("rewind"), "b4a.example.rewind");
	}

public boolean isSingleton() {
		return true;
	}
     public static RemoteObject getObject() {
		return myClass;
	 }

	public RemoteObject activityBA;
	public RemoteObject _activity;
    private PCBA pcBA;

	public PCBA create(Object[] args) throws ClassNotFoundException{
		processBA = (RemoteObject) args[1];
		activityBA = (RemoteObject) args[2];
		_activity = (RemoteObject) args[3];
        anywheresoftware.b4a.keywords.Common.Density = (Float)args[4];
        remoteMe = (RemoteObject) args[5];
		pcBA = new PCBA(this, rewind.class);
        main_subs_0.initializeProcessGlobals();
		return pcBA;
	}
public static RemoteObject __c = RemoteObject.declareNull("anywheresoftware.b4a.keywords.Common");
public static RemoteObject _xui = RemoteObject.declareNull("anywheresoftware.b4a.objects.B4XViewWrapper.XUI");
public static RemoteObject _tmr = RemoteObject.declareNull("anywheresoftware.b4a.objects.Timer");
public static RemoteObject _pnlbackground = RemoteObject.declareNull("anywheresoftware.b4a.objects.PanelWrapper");
public static RemoteObject _pnlcontent = RemoteObject.declareNull("anywheresoftware.b4a.objects.PanelWrapper");
public static RemoteObject _currentpanelindex = RemoteObject.createImmutable(0);
public static RemoteObject _progress = RemoteObject.createImmutable(0);
public static RemoteObject _panels = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.List");
public static RemoteObject _startx = RemoteObject.createImmutable(0f);
public static RemoteObject _originalx = RemoteObject.createImmutable(0f);
public static RemoteObject _slide_1 = RemoteObject.declareNull("anywheresoftware.b4a.objects.ImageViewWrapper");
public static b4a.example.main _main = null;
public static b4a.example.starter _starter = null;
public static b4a.example.m_funciones _m_funciones = null;
  public Object[] GetGlobals() {
		return new Object[] {"Activity",rewind.mostCurrent._activity,"currentPanelIndex",rewind._currentpanelindex,"m_Funciones",Debug.moduleToString(b4a.example.m_funciones.class),"Main",Debug.moduleToString(b4a.example.main.class),"originalX",rewind._originalx,"panels",rewind.mostCurrent._panels,"pnlBackground",rewind.mostCurrent._pnlbackground,"pnlContent",rewind.mostCurrent._pnlcontent,"progress",rewind._progress,"slide_1",rewind.mostCurrent._slide_1,"Starter",Debug.moduleToString(b4a.example.starter.class),"startX",rewind._startx,"tmr",rewind._tmr,"xui",rewind._xui};
}
}