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
	Private tmr As Timer
End Sub

Sub Globals
	'-------- BACKGROUNG --------'
	Private pnlBackground As Panel
	Private pnlContent As Panel
	'----------------------------'
	
	Dim currentPanelIndex As Int = 0
	Dim progress As Int 
	Dim panels As List 
	Dim startX As Float 
	Dim originalX As Float
	Dim canMove As Boolean = False
	
	Private slide_1 As ImageView 'IMAGEN PRINCIPAL'
	Private slide_2 As ImageView
	Private slide_3 As ImageView
	Private slide_4 As ImageView
	Private slide_5 As ImageView
	Private slide_6 As ImageView
	Private slide_7 As ImageView
End Sub

Sub Activity_Create(FirstTime As Boolean)
	m_Funciones.FullscreenActivity(Activity)
	Sleep(1)
	Activity.LoadLayout("Background.bal")
	
	pnlBackground.Height = Activity.Height + m_Funciones.GetStatusBarHeight + m_Funciones.GetNavigationBarHeight
	pnlContent.Height = pnlBackground.Height

	changeScreen(currentPanelIndex)
	CreateStatusIndicators(Activity)
	m_Funciones.AutoEscalar(Activity)
End Sub

Sub Activity_Resume

End Sub

Sub Activity_Pause (UserClosed As Boolean)

End Sub


Sub JobDone(Respuesta As String, job As HttpJob) As ResumableSub
	Try
		If job.Success Then
			Select job.JobName
				
					
			End Select
		End If
	Catch
		Log(LastException.Message)
	End Try
	Return Null
End Sub

'------------------------ CAROUSEL LOGIC ------------------------'

Sub changeScreen(screen As Int)
	If screen < 0 Or screen > 4 Then
		Return
	End If
	
	If currentPanelIndex = 4 And screen = 4 Then
		Return
	End If
	
	pnlContent.RemoveAllViews
	
	Select screen
		Case 0
			pnlContent.LoadLayout("slide1.bal")
			canMove = True
			AnimateSlideIn(slide_1)
			AnimateSlideInFromRight(slide_3)
			AnimateSlideInFromLeft(slide_2)
			AnimateSlideInFromTop(slide_4)
			Sleep(800)
			AnimateUpDown(slide_4)
			Sleep(800)
			AnimateUpDown(slide_2)
			AnimateUpDown(slide_3)
		
		Case 1
			pnlContent.LoadLayout("slide2.bal")
			AnimateSlideIn(slide_1)
			AnimateSlideInFromLeft(slide_2)
			AnimateSlideInFromRight(slide_3)
			Sleep(800)
			AnimateLeftToRight(slide_2)
			AnimateRightToLeft(slide_3)
		
		Case 2
			pnlContent.LoadLayout("slide3.bal")
			AnimateSlideIn(slide_1)
			AnimateSlideInFromRight(slide_2)
			AnimateSlideInFromRight(slide_3)
			AnimateSlideInFromRight(slide_4)
			AnimateSlideInFromRight(slide_5)
			AnimateSlideInFromRight(slide_6)
			AnimateSlideInFromRight(slide_7)
			
		Case 3
			pnlContent.LoadLayout("slide4.bal")
			AnimateSlideIn(slide_1)
			AnimateSlideInFromRight(slide_3)
			AnimateSlideInFromLeft(slide_2)
			
		Case 4
			pnlContent.LoadLayout("slide5.bal")
			AnimateSlideIn(slide_1)
			AnimateSlideInFromLeft(slide_3)
			AnimateSlideInFromLeft(slide_2)
			AnimateSlideInFromRight(slide_4)
			AnimateSlideInFromRight(slide_5)
			
	End Select
End Sub

Sub CreateStatusIndicators(act As Activity)
	Dim numPanels As Int = 5
	Dim panelHeight As Int = 5dip
	Dim leftMargin As Int = 30dip
	Dim spaceBetweenPanels As Int = 10dip

	Dim container As Panel
	container.Initialize("")
	container.Color = Colors.Transparent
	act.AddView(container, leftMargin, m_Funciones.GetStatusBarHeight + 30dip, act.Width - 2 * leftMargin, panelHeight)

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
		changeScreen(Min(currentPanelIndex + 1, panels.Size - 1))
		currentPanelIndex = Min(currentPanelIndex + 1, panels.Size - 1)
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
	canMove = False
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
	canMove = False
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

'------------------------ ANIMATIONS ------------------------'

Sub AnimateSlideIn(view As View)
	view.Top = Activity.Height + view.Height
	view.SetLayoutAnimated(800, view.Left, Activity.Height - view.Height, view.Width, view.Height)
End Sub

Sub AnimateSlideInFromTop(view As View)
	Dim originViewY As Float
	originViewY = view.Top
	view.Top = -view.Height
	view.SetLayoutAnimated(800, view.Left, originViewY, view.Width, view.Height)
End Sub

Sub AnimateSlideInFromRight(view As View)
	Dim originViewX As Float
	originViewX = view.Left
	view.Left = Activity.Width
	view.SetLayoutAnimated(800, originViewX, view.Top, view.Width, view.Height)
End Sub

Sub AnimateSlideInFromLeft(view As View)
	Dim originViewX As Float
	originViewX = view.Left
	view.Left = -view.Width
	view.SetLayoutAnimated(800, originViewX, view.Top, view.Width, view.Height)
End Sub

Sub AnimateUpDown(view As View)
	Dim originViewY As Float
	originViewY = view.Top
	Do While canMove = True
		view.SetLayoutAnimated(3000, view.Left, originViewY - 20dip, view.Width, view.Height)
		Sleep(3000)
		view.SetLayoutAnimated(3000, view.Left, originViewY + 20dip, view.Width, view.Height)
		Sleep(3000)
	Loop
End Sub

Sub AnimateLeftToRight(view As View)
	view.SetLayoutAnimated(30000, Activity.Width + view.Width, view.Top, view.Width, view.Height)
End Sub

Sub AnimateRightToLeft(view As View)
	view.SetLayoutAnimated(30000, -view.Width, view.Top, view.Width, view.Height)
End Sub

'------------------------------------------------------------'
