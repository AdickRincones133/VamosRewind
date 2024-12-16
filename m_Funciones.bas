B4A=true
Group=Default Group
ModulesStructureVersion=1
Type=StaticCode
Version=13
@EndOfDesignText@
'Code module
'Subs in this code module will be accessible from all modules.
Sub Process_Globals
	'These global variables will be declared once when the application starts.
	'These variables can be accessed from all modules.
	Private access As Accessiblity
End Sub

Sub FullscreenActivity (act As Activity)
	Dim p As Phone
	If p.SdkVersion >= 4.4 Then
		Dim jo As JavaObject
		Dim window As JavaObject = jo.InitializeContext.RunMethod("getWindow", Null)
		window.RunMethod("addFlags", Array(Bit.Or(0x00000200, 0x08000000)))
		
		' Ajustar altura considerando barra de estado y navegación
		Dim bodyHeight As Int = GetStatusBarHeight + GetNavigationBarHeight
		act.Height = act.Height + bodyHeight
	End If
End Sub

' Obtiene la altura de la barra de estado
Sub GetStatusBarHeight As Int
	Return GetDimension("status_bar_height")
End Sub

' Obtiene la altura de la barra de navegación
Sub GetNavigationBarHeight As Int
	Return GetDimension("navigation_bar_height")
End Sub

' Función auxiliar para obtener dimensiones por recurso
Private Sub GetDimension(resourceName As String) As Int
	Dim context As JavaObject
	context.InitializeContext
	Dim res As JavaObject = context.RunMethod("getResources", Null)
	Dim resourceId As Int = res.RunMethod("getIdentifier", Array(resourceName, "dimen", "android"))
	If resourceId > 0 Then
		Return res.RunMethod("getDimensionPixelSize", Array(resourceId))
	Else
		Return 0
	End If
End Sub

' Ajusta la escala de texto automáticamente según el factor de escala del usuario
Public Sub AutoEscalar(Pantalla As Activity)
	Dim fscale As Double = access.GetUserFontScale
	If fscale > 1 Then
		For Each v As View In Pantalla.GetAllViewsRecursive
			AdjustTextSize(v, fscale)
		Next
	End If
End Sub

' Ajusta el tamaño del texto de un View (Label o Button)
Private Sub AdjustTextSize(v As View, fscale As Double)
	If v Is Label Then
		Dim lbl As Label = v
		lbl.TextSize = Max(10, (lbl.TextSize / fscale) - 2) ' Asegura un tamaño mínimo
	Else If v Is Button Then
		Dim btn As Button = v
		btn.TextSize = Max(10, btn.TextSize / fscale) ' Asegura un tamaño mínimo
	End If
End Sub