
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

public class m_funciones implements IRemote{
	public static m_funciones mostCurrent;
	public static RemoteObject processBA;
    public static boolean processGlobalsRun;
    public static RemoteObject myClass;
    public static RemoteObject remoteMe;
	public m_funciones() {
		mostCurrent = this;
	}
    public RemoteObject getRemoteMe() {
        return remoteMe;    
    }
    
public boolean isSingleton() {
		return true;
	}
     private static PCBA pcBA = new PCBA(null, m_funciones.class);
    static {
		mostCurrent = new m_funciones();
        remoteMe = RemoteObject.declareNull("b4a.example.m_funciones");
        anywheresoftware.b4a.pc.RapidSub.moduleToObject.put(new B4XClass("m_funciones"), "b4a.example.m_funciones");
        RDebug.INSTANCE.eventTargets.put(new DeviceClass("b4a.example.m_funciones"), new java.lang.ref.WeakReference<PCBA> (pcBA));
	}
   
	public static RemoteObject runMethod(boolean notUsed, String method, Object... args) throws Exception{
		return (RemoteObject) pcBA.raiseEvent(method.substring(1), args);
	}
    public static void runVoidMethod(String method, Object... args) throws Exception{
		runMethod(false, method, args);
	}
	public PCBA create(Object[] args) throws ClassNotFoundException{
        throw new RuntimeException("CREATE is not supported.");
	}
public static RemoteObject __c = RemoteObject.declareNull("anywheresoftware.b4a.keywords.Common");
public static RemoteObject _access = RemoteObject.declareNull("anywheresoftware.b4a.objects.Accessibility.Accessibility2");
public static b4a.example.main _main = null;
public static b4a.example.starter _starter = null;
public static b4a.example.rewind _rewind = null;
  public Object[] GetGlobals() {
		return new Object[] {"access",m_funciones._access,"Main",Debug.moduleToString(b4a.example.main.class),"Rewind",Debug.moduleToString(b4a.example.rewind.class),"Starter",Debug.moduleToString(b4a.example.starter.class)};
}
}