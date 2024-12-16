package b4a.example;


import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.objects.ServiceHelper;
import anywheresoftware.b4a.debug.*;

public class starter extends android.app.Service{
	public static class starter_BR extends android.content.BroadcastReceiver {

		@Override
		public void onReceive(android.content.Context context, android.content.Intent intent) {
            BA.LogInfo("** Receiver (starter) OnReceive **");
			android.content.Intent in = new android.content.Intent(context, starter.class);
			if (intent != null)
				in.putExtra("b4a_internal_intent", intent);
            ServiceHelper.StarterHelper.startServiceFromReceiver (context, in, true, BA.class);
		}

	}
    static starter mostCurrent;
	public static BA processBA;
    private ServiceHelper _service;
    public static Class<?> getObject() {
		return starter.class;
	}
	@Override
	public void onCreate() {
        super.onCreate();
        mostCurrent = this;
        if (processBA == null) {
		    processBA = new BA(this, null, null, "b4a.example", "b4a.example.starter");
            if (BA.isShellModeRuntimeCheck(processBA)) {
                processBA.raiseEvent2(null, true, "SHELL", false);
		    }
            try {
                Class.forName(BA.applicationContext.getPackageName() + ".main").getMethod("initializeProcessGlobals").invoke(null, null);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            processBA.loadHtSubs(this.getClass());
            ServiceHelper.init();
        }
        _service = new ServiceHelper(this);
        processBA.service = this;
        
        if (BA.isShellModeRuntimeCheck(processBA)) {
			processBA.raiseEvent2(null, true, "CREATE", true, "b4a.example.starter", processBA, _service, anywheresoftware.b4a.keywords.Common.Density);
		}
        if (!true && ServiceHelper.StarterHelper.startFromServiceCreate(processBA, false) == false) {
				
		}
		else {
            processBA.setActivityPaused(false);
            BA.LogInfo("*** Service (starter) Create ***");
            processBA.raiseEvent(null, "service_create");
        }
        processBA.runHook("oncreate", this, null);
        if (true) {
			if (ServiceHelper.StarterHelper.runWaitForLayouts() == false) {
                BA.LogInfo("stopping spontaneous created service");
                stopSelf();
            }
		}
    }
		@Override
	public void onStart(android.content.Intent intent, int startId) {
		onStartCommand(intent, 0, 0);
    }
    @Override
    public int onStartCommand(final android.content.Intent intent, int flags, int startId) {
    	if (ServiceHelper.StarterHelper.onStartCommand(processBA, new Runnable() {
            public void run() {
                handleStart(intent);
            }}))
			;
		else {
			ServiceHelper.StarterHelper.addWaitForLayout (new Runnable() {
				public void run() {
                    processBA.setActivityPaused(false);
                    BA.LogInfo("** Service (starter) Create **");
                    processBA.raiseEvent(null, "service_create");
					handleStart(intent);
                    ServiceHelper.StarterHelper.removeWaitForLayout();
				}
			});
		}
        processBA.runHook("onstartcommand", this, new Object[] {intent, flags, startId});
		return android.app.Service.START_NOT_STICKY;
    }
    public void onTaskRemoved(android.content.Intent rootIntent) {
        super.onTaskRemoved(rootIntent);
        if (true)
            processBA.raiseEvent(null, "service_taskremoved");
            
    }
    private void handleStart(android.content.Intent intent) {
    	BA.LogInfo("** Service (starter) Start **");
    	java.lang.reflect.Method startEvent = processBA.htSubs.get("service_start");
    	if (startEvent != null) {
    		if (startEvent.getParameterTypes().length > 0) {
    			anywheresoftware.b4a.objects.IntentWrapper iw = ServiceHelper.StarterHelper.handleStartIntent(intent, _service, processBA);
    			processBA.raiseEvent(null, "service_start", iw);
    		}
    		else {
    			processBA.raiseEvent(null, "service_start");
    		}
    	}
    }

	public void onTimeout(int startId) {
        BA.LogInfo("** Service (starter) Timeout **");
        anywheresoftware.b4a.objects.collections.Map params = new anywheresoftware.b4a.objects.collections.Map();
        params.Initialize();
        params.Put("StartId", startId);
        processBA.raiseEvent(null, "service_timeout", params);
            
    }
	@Override
	public void onDestroy() {
        super.onDestroy();
        if (true) {
            BA.LogInfo("** Service (starter) Destroy (ignored)**");
        }
        else {
            BA.LogInfo("** Service (starter) Destroy **");
		    processBA.raiseEvent(null, "service_destroy");
            processBA.service = null;
		    mostCurrent = null;
		    processBA.setActivityPaused(true);
            processBA.runHook("ondestroy", this, null);
        }
	}

@Override
	public android.os.IBinder onBind(android.content.Intent intent) {
		return null;
	}public anywheresoftware.b4a.keywords.Common __c = null;
public static String _ip_conexion = "";
public b4a.example.main _main = null;
public b4a.example.rewind _rewind = null;
public b4a.example.m_funciones _m_funciones = null;
public b4a.example.httputils2service _httputils2service = null;
public static boolean  _application_error(anywheresoftware.b4a.objects.B4AException _error,String _stacktrace) throws Exception{
 //BA.debugLineNum = 21;BA.debugLine="Sub Application_Error (Error As Exception, StackTr";
 //BA.debugLineNum = 22;BA.debugLine="Return True";
if (true) return anywheresoftware.b4a.keywords.Common.True;
 //BA.debugLineNum = 23;BA.debugLine="End Sub";
return false;
}
public static anywheresoftware.b4a.keywords.Common.ResumableSubWrapper  _get_query(String _query,String _jobname) throws Exception{
ResumableSub_GET_Query rsub = new ResumableSub_GET_Query(null,_query,_jobname);
rsub.resume(processBA, null);
return (anywheresoftware.b4a.keywords.Common.ResumableSubWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.keywords.Common.ResumableSubWrapper(), rsub);
}
public static class ResumableSub_GET_Query extends BA.ResumableSub {
public ResumableSub_GET_Query(b4a.example.starter parent,String _query,String _jobname) {
this.parent = parent;
this._query = _query;
this._jobname = _jobname;
}
b4a.example.starter parent;
String _query;
String _jobname;
String _res = "";
b4a.example.httpjob _job = null;
Object[] _actividad = null;

@Override
public void resume(BA ba, Object[] result) throws Exception{

    while (true) {
try {

        switch (state) {
            case -1:
{
anywheresoftware.b4a.keywords.Common.ReturnFromResumableSub(this,null);return;}
case 0:
//C
this.state = 1;
 //BA.debugLineNum = 52;BA.debugLine="Try";
if (true) break;

case 1:
//try
this.state = 14;
this.catchState = 13;
this.state = 3;
if (true) break;

case 3:
//C
this.state = 4;
this.catchState = 13;
 //BA.debugLineNum = 53;BA.debugLine="Dim res As String";
_res = "";
 //BA.debugLineNum = 54;BA.debugLine="Dim job As HttpJob";
_job = new b4a.example.httpjob();
 //BA.debugLineNum = 55;BA.debugLine="job.Initialize(JobName, Me)";
_job._initialize /*String*/ (processBA,_jobname,starter.getObject());
 //BA.debugLineNum = 56;BA.debugLine="job.PostString(\"http://\"&IP_Conexion&\"/spa/selec";
_job._poststring /*String*/ ("http://"+parent._ip_conexion+"/spa/selects.php","query="+_query);
 //BA.debugLineNum = 57;BA.debugLine="Wait For (job) jobDone(job As HttpJob)";
anywheresoftware.b4a.keywords.Common.WaitFor("jobdone", processBA, this, (Object)(_job));
this.state = 15;
return;
case 15:
//C
this.state = 4;
_job = (b4a.example.httpjob) result[0];
;
 //BA.debugLineNum = 58;BA.debugLine="If job.success Then";
if (true) break;

case 4:
//if
this.state = 11;
if (_job._success /*boolean*/ ) { 
this.state = 6;
}if (true) break;

case 6:
//C
this.state = 7;
 //BA.debugLineNum = 59;BA.debugLine="res = job.GetString2(\"Windows-1252\")";
_res = _job._getstring2 /*String*/ ("Windows-1252");
 //BA.debugLineNum = 60;BA.debugLine="Dim actividad() = Regex.Split(\",\", JobName) As";
_actividad = (Object[])(anywheresoftware.b4a.keywords.Common.Regex.Split(",",_jobname));
 //BA.debugLineNum = 61;BA.debugLine="If Not(IsPaused(actividad(1))) Then";
if (true) break;

case 7:
//if
this.state = 10;
if (anywheresoftware.b4a.keywords.Common.Not(anywheresoftware.b4a.keywords.Common.IsPaused(processBA,_actividad[(int) (1)]))) { 
this.state = 9;
}if (true) break;

case 9:
//C
this.state = 10;
 //BA.debugLineNum = 62;BA.debugLine="CallSub3(actividad(1), \"JobDone\", res, job)";
anywheresoftware.b4a.keywords.Common.CallSubNew3(processBA,_actividad[(int) (1)],"JobDone",(Object)(_res),(Object)(_job));
 if (true) break;

case 10:
//C
this.state = 11;
;
 if (true) break;

case 11:
//C
this.state = 14;
;
 if (true) break;

case 13:
//C
this.state = 14;
this.catchState = 0;
 //BA.debugLineNum = 66;BA.debugLine="Log(LastException.Message)";
anywheresoftware.b4a.keywords.Common.LogImpl("55308431",anywheresoftware.b4a.keywords.Common.LastException(processBA).getMessage(),0);
 if (true) break;
if (true) break;

case 14:
//C
this.state = -1;
this.catchState = 0;
;
 //BA.debugLineNum = 68;BA.debugLine="Return Null";
if (true) {
anywheresoftware.b4a.keywords.Common.ReturnFromResumableSub(this,anywheresoftware.b4a.keywords.Common.Null);return;};
 //BA.debugLineNum = 69;BA.debugLine="End Sub";
if (true) break;
}} 
       catch (Exception e0) {
			
if (catchState == 0)
    throw e0;
else {
    state = catchState;
processBA.setLastException(e0);}
            }
        }
    }
}
public static void  _jobdone(b4a.example.httpjob _job) throws Exception{
}
public static anywheresoftware.b4a.keywords.Common.ResumableSubWrapper  _post_query(String _query,String _jobname) throws Exception{
ResumableSub_POST_Query rsub = new ResumableSub_POST_Query(null,_query,_jobname);
rsub.resume(processBA, null);
return (anywheresoftware.b4a.keywords.Common.ResumableSubWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.keywords.Common.ResumableSubWrapper(), rsub);
}
public static class ResumableSub_POST_Query extends BA.ResumableSub {
public ResumableSub_POST_Query(b4a.example.starter parent,String _query,String _jobname) {
this.parent = parent;
this._query = _query;
this._jobname = _jobname;
}
b4a.example.starter parent;
String _query;
String _jobname;
String _res = "";
b4a.example.httpjob _job = null;
Object[] _actividad = null;

@Override
public void resume(BA ba, Object[] result) throws Exception{

    while (true) {
try {

        switch (state) {
            case -1:
{
anywheresoftware.b4a.keywords.Common.ReturnFromResumableSub(this,null);return;}
case 0:
//C
this.state = 1;
 //BA.debugLineNum = 30;BA.debugLine="Try";
if (true) break;

case 1:
//try
this.state = 14;
this.catchState = 13;
this.state = 3;
if (true) break;

case 3:
//C
this.state = 4;
this.catchState = 13;
 //BA.debugLineNum = 31;BA.debugLine="Dim res As String";
_res = "";
 //BA.debugLineNum = 32;BA.debugLine="Dim job As HttpJob";
_job = new b4a.example.httpjob();
 //BA.debugLineNum = 33;BA.debugLine="job.Initialize(JobName, Me)";
_job._initialize /*String*/ (processBA,_jobname,starter.getObject());
 //BA.debugLineNum = 34;BA.debugLine="job.PostString($\"http://${IP_Conexion}/spa/inset";
_job._poststring /*String*/ (("http://"+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(parent._ip_conexion))+"/spa/insetar_cliente.php"),"query="+_query);
 //BA.debugLineNum = 36;BA.debugLine="Wait For (job) JobDone(job As HttpJob)";
anywheresoftware.b4a.keywords.Common.WaitFor("jobdone", processBA, this, (Object)(_job));
this.state = 15;
return;
case 15:
//C
this.state = 4;
_job = (b4a.example.httpjob) result[0];
;
 //BA.debugLineNum = 37;BA.debugLine="If job.success Then";
if (true) break;

case 4:
//if
this.state = 11;
if (_job._success /*boolean*/ ) { 
this.state = 6;
}if (true) break;

case 6:
//C
this.state = 7;
 //BA.debugLineNum = 38;BA.debugLine="res = job.GetString2(\"Windows-1252\")";
_res = _job._getstring2 /*String*/ ("Windows-1252");
 //BA.debugLineNum = 39;BA.debugLine="Dim actividad() = Regex.Split(\",\", JobName) As";
_actividad = (Object[])(anywheresoftware.b4a.keywords.Common.Regex.Split(",",_jobname));
 //BA.debugLineNum = 40;BA.debugLine="If Not(IsPaused(actividad(1))) Then";
if (true) break;

case 7:
//if
this.state = 10;
if (anywheresoftware.b4a.keywords.Common.Not(anywheresoftware.b4a.keywords.Common.IsPaused(processBA,_actividad[(int) (1)]))) { 
this.state = 9;
}if (true) break;

case 9:
//C
this.state = 10;
 //BA.debugLineNum = 41;BA.debugLine="CallSub3(actividad(1), \"JobDone\", res, job)";
anywheresoftware.b4a.keywords.Common.CallSubNew3(processBA,_actividad[(int) (1)],"JobDone",(Object)(_res),(Object)(_job));
 if (true) break;

case 10:
//C
this.state = 11;
;
 if (true) break;

case 11:
//C
this.state = 14;
;
 if (true) break;

case 13:
//C
this.state = 14;
this.catchState = 0;
 //BA.debugLineNum = 45;BA.debugLine="Log(LastException.Message)";
anywheresoftware.b4a.keywords.Common.LogImpl("56094864",anywheresoftware.b4a.keywords.Common.LastException(processBA).getMessage(),0);
 if (true) break;
if (true) break;

case 14:
//C
this.state = -1;
this.catchState = 0;
;
 //BA.debugLineNum = 47;BA.debugLine="Return Null";
if (true) {
anywheresoftware.b4a.keywords.Common.ReturnFromResumableSub(this,anywheresoftware.b4a.keywords.Common.Null);return;};
 //BA.debugLineNum = 48;BA.debugLine="End Sub";
if (true) break;
}} 
       catch (Exception e0) {
			
if (catchState == 0)
    throw e0;
else {
    state = catchState;
processBA.setLastException(e0);}
            }
        }
    }
}
public static String  _process_globals() throws Exception{
 //BA.debugLineNum = 6;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 7;BA.debugLine="Public IP_Conexion As String = \"149.71.98.251\"";
_ip_conexion = "149.71.98.251";
 //BA.debugLineNum = 8;BA.debugLine="End Sub";
return "";
}
public static String  _service_create() throws Exception{
 //BA.debugLineNum = 10;BA.debugLine="Sub Service_Create";
 //BA.debugLineNum = 12;BA.debugLine="End Sub";
return "";
}
public static String  _service_destroy() throws Exception{
 //BA.debugLineNum = 25;BA.debugLine="Sub Service_Destroy";
 //BA.debugLineNum = 27;BA.debugLine="End Sub";
return "";
}
public static String  _service_start(anywheresoftware.b4a.objects.IntentWrapper _startingintent) throws Exception{
 //BA.debugLineNum = 14;BA.debugLine="Sub Service_Start (StartingIntent As Intent)";
 //BA.debugLineNum = 15;BA.debugLine="Service.StopAutomaticForeground";
mostCurrent._service.StopAutomaticForeground();
 //BA.debugLineNum = 16;BA.debugLine="End Sub";
return "";
}
public static String  _service_taskremoved() throws Exception{
 //BA.debugLineNum = 18;BA.debugLine="Sub Service_TaskRemoved";
 //BA.debugLineNum = 19;BA.debugLine="End Sub";
return "";
}
public static anywheresoftware.b4a.keywords.Common.ResumableSubWrapper  _update_query(String _query,String _jobname) throws Exception{
ResumableSub_UPDATE_Query rsub = new ResumableSub_UPDATE_Query(null,_query,_jobname);
rsub.resume(processBA, null);
return (anywheresoftware.b4a.keywords.Common.ResumableSubWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.keywords.Common.ResumableSubWrapper(), rsub);
}
public static class ResumableSub_UPDATE_Query extends BA.ResumableSub {
public ResumableSub_UPDATE_Query(b4a.example.starter parent,String _query,String _jobname) {
this.parent = parent;
this._query = _query;
this._jobname = _jobname;
}
b4a.example.starter parent;
String _query;
String _jobname;
String _res = "";
b4a.example.httpjob _job = null;
Object[] _actividad = null;

@Override
public void resume(BA ba, Object[] result) throws Exception{

    while (true) {
try {

        switch (state) {
            case -1:
{
anywheresoftware.b4a.keywords.Common.ReturnFromResumableSub(this,null);return;}
case 0:
//C
this.state = 1;
 //BA.debugLineNum = 72;BA.debugLine="Try";
if (true) break;

case 1:
//try
this.state = 14;
this.catchState = 13;
this.state = 3;
if (true) break;

case 3:
//C
this.state = 4;
this.catchState = 13;
 //BA.debugLineNum = 73;BA.debugLine="Dim res As String";
_res = "";
 //BA.debugLineNum = 74;BA.debugLine="Dim job As HttpJob";
_job = new b4a.example.httpjob();
 //BA.debugLineNum = 75;BA.debugLine="job.Initialize(JobName, Me)";
_job._initialize /*String*/ (processBA,_jobname,starter.getObject());
 //BA.debugLineNum = 76;BA.debugLine="job.PostString(\"http://\"&IP_Conexion&\"/spa/updat";
_job._poststring /*String*/ ("http://"+parent._ip_conexion+"/spa/update.php","query="+_query);
 //BA.debugLineNum = 77;BA.debugLine="Wait For (job) jobDone(job As HttpJob)";
anywheresoftware.b4a.keywords.Common.WaitFor("jobdone", processBA, this, (Object)(_job));
this.state = 15;
return;
case 15:
//C
this.state = 4;
_job = (b4a.example.httpjob) result[0];
;
 //BA.debugLineNum = 78;BA.debugLine="If job.success Then";
if (true) break;

case 4:
//if
this.state = 11;
if (_job._success /*boolean*/ ) { 
this.state = 6;
}if (true) break;

case 6:
//C
this.state = 7;
 //BA.debugLineNum = 79;BA.debugLine="res = job.GetString2(\"Windows-1252\")";
_res = _job._getstring2 /*String*/ ("Windows-1252");
 //BA.debugLineNum = 80;BA.debugLine="Dim actividad() = Regex.Split(\",\", JobName) As";
_actividad = (Object[])(anywheresoftware.b4a.keywords.Common.Regex.Split(",",_jobname));
 //BA.debugLineNum = 81;BA.debugLine="If Not(IsPaused(actividad(1))) Then";
if (true) break;

case 7:
//if
this.state = 10;
if (anywheresoftware.b4a.keywords.Common.Not(anywheresoftware.b4a.keywords.Common.IsPaused(processBA,_actividad[(int) (1)]))) { 
this.state = 9;
}if (true) break;

case 9:
//C
this.state = 10;
 //BA.debugLineNum = 82;BA.debugLine="CallSub3(actividad(1), \"JobDone\", res, job)";
anywheresoftware.b4a.keywords.Common.CallSubNew3(processBA,_actividad[(int) (1)],"JobDone",(Object)(_res),(Object)(_job));
 if (true) break;

case 10:
//C
this.state = 11;
;
 if (true) break;

case 11:
//C
this.state = 14;
;
 if (true) break;

case 13:
//C
this.state = 14;
this.catchState = 0;
 //BA.debugLineNum = 86;BA.debugLine="Log(LastException.Message)";
anywheresoftware.b4a.keywords.Common.LogImpl("56881295",anywheresoftware.b4a.keywords.Common.LastException(processBA).getMessage(),0);
 if (true) break;
if (true) break;

case 14:
//C
this.state = -1;
this.catchState = 0;
;
 //BA.debugLineNum = 88;BA.debugLine="Return Null";
if (true) {
anywheresoftware.b4a.keywords.Common.ReturnFromResumableSub(this,anywheresoftware.b4a.keywords.Common.Null);return;};
 //BA.debugLineNum = 89;BA.debugLine="End Sub";
if (true) break;
}} 
       catch (Exception e0) {
			
if (catchState == 0)
    throw e0;
else {
    state = catchState;
processBA.setLastException(e0);}
            }
        }
    }
}
}
