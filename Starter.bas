B4A=true
Group=Default Group
ModulesStructureVersion=1
Type=Service
Version=9.9
@EndOfDesignText@
#Region  Service Attributes 
	#StartAtBoot: False
	#ExcludeFromLibrary: True
#End Region

Sub Process_Globals
	Public IP_Conexion As String = "149.71.98.251"
End Sub

Sub Service_Create

End Sub

Sub Service_Start (StartingIntent As Intent)
	Service.StopAutomaticForeground 
End Sub

Sub Service_TaskRemoved
End Sub

Sub Application_Error (Error As Exception, StackTrace As String) As Boolean
	Return True
End Sub

Sub Service_Destroy

End Sub

Public Sub POST_Query(Query As String, JobName As String) As ResumableSub
	Try
		Dim res As String
		Dim job As HttpJob
		job.Initialize(JobName, Me)
		job.PostString($"http://${IP_Conexion}/spa/insetar_cliente.php"$, "query=" & Query) 

		Wait For (job) JobDone(job As HttpJob)
		If job.success Then
			res = job.GetString2("Windows-1252")
			Dim actividad() = Regex.Split(",", JobName) As Object
			If Not(IsPaused(actividad(1))) Then
				CallSub3(actividad(1), "JobDone", res, job)
			End If
		End If
	Catch
		Log(LastException.Message)
	End Try
	Return Null
End Sub


Sub GET_Query(Query As String, JobName As String) As ResumableSub
	Try
		Dim res As String
		Dim job As HttpJob
		job.Initialize(JobName, Me)
		job.PostString("http://"&IP_Conexion&"/spa/selects.php", "query=" & Query)
		Wait For (job) jobDone(job As HttpJob)
		If job.success Then
			res = job.GetString2("Windows-1252")
			Dim actividad() = Regex.Split(",", JobName) As Object
			If Not(IsPaused(actividad(1))) Then
				CallSub3(actividad(1), "JobDone", res, job)
			End If
		End If
	Catch
		Log(LastException.Message)
	End Try
	Return Null
End Sub

Sub UPDATE_Query(Query As String, JobName As String) As ResumableSub
	Try
		Dim res As String
		Dim job As HttpJob
		job.Initialize(JobName, Me)
		job.PostString("http://"&IP_Conexion&"/spa/update.php", "query=" & Query)
		Wait For (job) jobDone(job As HttpJob)
		If job.success Then
			res = job.GetString2("Windows-1252")
			Dim actividad() = Regex.Split(",", JobName) As Object
			If Not(IsPaused(actividad(1))) Then
				CallSub3(actividad(1), "JobDone", res, job)
			End If
		End If
	Catch
		Log(LastException.Message)
	End Try
	Return Null
End Sub