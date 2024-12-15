B4A=true
Group=Default Group
ModulesStructureVersion=1
Type=Activity
Version=13
@EndOfDesignText@
#Region  Activity Attributes 
	#FullScreen: False
	#IncludeTitle: False
#End Region

Sub Process_Globals
	Private xui As XUI
	Private access As Accessiblity

End Sub

Sub Globals
	'-------- BACKGROUNG --------'
	Private pnlBackground As Panel
	Private pnlContent As Panel
	'----------------------------'
	
	Dim currentPanelIndex As Int = 0
	Dim progress As Int 
	Dim panels As List 
	Dim tmr As Timer 
	Dim startX As Float 
	Dim originalX As Float
	
	Private slide_1 As ImageView 'IMAGEN PRINCIPAL'
End Sub

Sub Activity_Create(FirstTime As Boolean)
	FullscreenActivity(Activity)
	Activity.LoadLayout("Background.bal")
	
	pnlBackground.Height = Activity.Height + GetStatusBarHeight + GetNavigationBarHeight
	pnlContent.Height = pnlBackground.Height

	changeScreen(currentPanelIndex)
	CreateStatusIndicators(Activity)
	AutoEscalar(Activity)
End Sub

Sub Activity_Resume

End Sub

Sub Activity_Pause (UserClosed As Boolean)

End Sub

'------------------------ CAROUSEL LOGIC ------------------------'

Sub changeScreen(screen As Int)
	If screen < 0 Or screen > 4 Then
		Return
	End If
	
	pnlContent.RemoveAllViews
	
	Select screen
		Case 0
			pnlContent.LoadLayout("slide1.bal")
			AnimateSlideIn(slide_1)
		
		Case 1
			pnlContent.LoadLayout("slide2.bal")
			AnimateSlideIn(slide_1)
		
		Case 2
			pnlContent.LoadLayout("slide3.bal")
			AnimateSlideIn(slide_1)
			
		Case 3
			pnlContent.LoadLayout("slide4.bal")
			AnimateSlideIn(slide_1)
			
		Case 4
			pnlContent.LoadLayout("slide5.bal")
			AnimateSlideIn(slide_1)
			
	End Select
End Sub

Sub AnimateSlideIn(view As View)	
	view.Top = Activity.Height + view.Height
	view.SetLayoutAnimated(800, view.Left, Activity.Height - view.Height, view.Width, view.Height)
End Sub

Sub CreateStatusIndicators(act As Activity)
	Dim numPanels As Int = 5
	Dim panelHeight As Int = 5dip
	Dim leftMargin As Int = 30dip
	Dim spaceBetweenPanels As Int = 10dip

	Dim container As Panel
	container.Initialize("")
	container.Color = Colors.Transparent
	act.AddView(container, leftMargin, GetStatusBarHeight + 30dip, act.Width - 2 * leftMargin, panelHeight)

	Dim containerWidth As Int = act.Width - 2 * leftMargin
	Dim totalSpace As Int = (numPanels - 1) * spaceBetweenPanels
	Dim panelWidth As Int = (containerWidth - totalSpace) / numPanels

	panels.Initialize

	For i = 0 To numPanels - 1
		Dim panel As Panel
		panel.Initialize("")
		panel.Color = Colors.ARGB(200, 255, 255, 180) 
		container.AddView(panel, (panelWidth + spaceBetweenPanels) * i, 0, panelWidth, panelHeight)
		panels.Add(panel)
	Next

	FillPanelsProgressively
End Sub

Sub FillPanelsProgressively
	currentPanelIndex = 0
	progress = 0

	tmr.Initialize("FillTimer", 500) 
	tmr.Enabled = True
End Sub

Sub FillTimer_Tick
	If currentPanelIndex >= panels.Size Then
		tmr.Enabled = False
		Return
	End If

	Dim panel As Panel = panels.Get(currentPanelIndex)
	Dim panelWidth As Int = panel.Width

	Dim canvas As Canvas
	canvas.Initialize(panel)
	Dim rect As Rect
	rect.Initialize(0, 0, progress, panel.Height)
	canvas.DrawRect(rect, Colors.White, True, 0) 

	progress = progress + 5dip 

	If progress >= panelWidth Then
		currentPanelIndex = Min(currentPanelIndex + 1, panels.Size - 1)
		changeScreen(currentPanelIndex)
		progress = 0
	End If
End Sub

Sub pnlContent_Touch(Action As Int, X As Float, Y As Float)
	Select Case Action
		Case Activity.ACTION_DOWN
			startX = X
			originalX = pnlContent.Left
		Case Activity.ACTION_MOVE
			Dim deltaX As Float = X - startX
			pnlContent.Left = Max(Min(originalX + deltaX, originalX + 100dip), originalX - 100dip)
		Case Activity.ACTION_UP
			Dim deltaX As Float = X - startX
            
			If Abs(deltaX) > 50dip Then
				If deltaX < 0 Then
					FillCurrentBarAndAdvance 
				Else If deltaX > 0 Then
					ResetCurrentBarAndGoBack 
				End If
			Else
				Dim panelWidth As Int = pnlContent.Width / 2
				If X < panelWidth Then
					ResetCurrentBarAndGoBack 
				Else
					FillCurrentBarAndAdvance 
				End If
			End If
            
			' Animar el regreso del panel a su posición original
			pnlContent.SetLayoutAnimated(300, originalX, pnlContent.Top, pnlContent.Width, pnlContent.Height)
	End Select
End Sub


Sub FillCurrentBarAndAdvance
	changeScreen(currentPanelIndex + 1)
	
	If currentPanelIndex >= panels.Size Then Return

	Dim panel As Panel = panels.Get(currentPanelIndex)
	Dim canvas As Canvas
	canvas.Initialize(panel)
	Dim rect As Rect
	rect.Initialize(0, 0, panel.Width, panel.Height)
	canvas.DrawRect(rect, Colors.White, True, 0) 

	currentPanelIndex = Min(currentPanelIndex + 1, panels.Size - 1)
	progress = 0
End Sub

Sub ResetCurrentBarAndGoBack
	changeScreen(currentPanelIndex - 1)
	
	If currentPanelIndex <= 0 Then Return 

	Dim panel As Panel = panels.Get(currentPanelIndex)
	Dim canvas As Canvas
	canvas.Initialize(panel)
	Dim rect As Rect
	rect.Initialize(0, 0, panel.Width, panel.Height) 
	canvas.DrawRect(rect, Colors.ARGB(200, 255, 255, 180), True, 0) 

	currentPanelIndex = Max(currentPanelIndex - 1, 0)

	panel = panels.Get(currentPanelIndex)
	canvas.Initialize(panel)
	rect.Initialize(0, 0, panel.Width, panel.Height) 
	canvas.DrawRect(rect, Colors.ARGB(200, 255, 255, 180), True, 0) 

	progress = 0
End Sub

'----------------------------------------------------------------'

'------------------------ FULL SCREEN CONFIGURATION ------------------------'

Sub FullscreenActivity (act As Activity)
	Dim p As Phone
	Dim bodyHeight As Int = GetStatusBarHeight + GetNavigationBarHeight
	If p.SdkVersion >= 4.4 Then
		Dim jo As JavaObject
		Dim window As JavaObject = jo.InitializeContext.RunMethod("getWindow", Null)
		window.RunMethod("addFlags", Array(Bit.Or(0x00000200, 0x08000000)))
		act.Height = act.Height + bodyHeight
	End If
End Sub

Sub GetStatusBarHeight As Int
	Dim context As JavaObject
	context.InitializeContext
	Dim res As JavaObject = context.RunMethod("getResources", Null)
	Dim resourceId As Int = res.RunMethod("getIdentifier", Array("status_bar_height", "dimen", "android"))
	If resourceId > 0 Then
		Return res.RunMethod("getDimensionPixelSize", Array(resourceId))
	Else
		Return 0
	End If
End Sub

Sub GetNavigationBarHeight As Int
	Dim context As JavaObject
	context.InitializeContext
	Dim res As JavaObject = context.RunMethod("getResources", Null)
	Dim resourceId As Int = res.RunMethod("getIdentifier", Array("navigation_bar_height", "dimen", "android"))
	If resourceId > 0 Then
		Return res.RunMethod("getDimensionPixelSize", Array(resourceId))
	Else
		Return 0
	End If
End Sub
Public Sub AutoEscalar(Pantalla As Activity)
	Dim fscale As Double
	
	fscale = access.GetUserFontScale
	If fscale > 1 Then
		For Each v As View In Pantalla.GetAllViewsRecursive
			If v Is Label Then
				Dim lbl As Label = v
				lbl.TextSize = NumberFormat2((lbl.TextSize / fscale) - 2,1,0,0,False)
				Log((lbl.TextSize / fscale) - 1)
			Else If v Is Button Then
				Dim s As Button = v
				s.TextSize = NumberFormat2(s.TextSize / fscale,1,0,0,False)
			End If
		Next
	End If
End Sub

'---------------------------------------------------------------------------'