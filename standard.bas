Attribute VB_Name = "standard"
Sub openBook()

    Dim OpenFileName As String
    Dim dirName As String
    Dim pathName As String
    Dim FileName As String
    
    
    dirName = "C"
    pathName = "C:\MyDoc\plan"
    
    ChDrive dirName
    ChDir pathName
    OpenFileName = Application.GetOpenFilename("Microsoft Excelブック,*.xlsx")
    
    'MsgBox OpenFileName
    
    If OpenFileName <> "False" Then
    
        
        Dim xlBook
        Set xlBook = Workbooks.Open(OpenFileName)
               
        
        xlBook.Worksheets("price").Activate          'ワークシートをアクティブにする ※１
        Range("A1:Q22").Copy                         'コピーする ※２
        ActiveSheet.Paste Destination:=ThisWorkbook.Worksheets("price").Range("A1:Q22") '貼り付ける ※３、４
        
        
        
        Application.DisplayAlerts = Flse
        xlBook.Close
        Application.DisplayAlerts = True
        
        ThisWorkbook.Worksheets("plan").Activate
        
        'FileName = Dir(OpenFileName)
        ThisWorkbook.Worksheets("menu").Range("A7").MergeArea = Dir(OpenFileName)
        
    
    End If


End Sub

Sub savePlan()


    Dim SaveDir As String
    Dim pathName As String
    
    
    'dirName = "C"
    pathName = ThisWorkbook.Worksheets("menu").Range("A12").Text

    MsgBox pathName

    If Dir(pathName, vbDirectory) = "" Then
        MkDir pathName
    End If
    
    
    Dim printSheet As String
    Dim customerName As String

    printSheet = "plan"
    customerName = ThisWorkbook.Worksheets("plan").Range("B1")
    
    MsgBox customerName

    Call printPDF(printSheet, customerName)


End Sub


' printSheetがPDF印刷するシート名、customerNameは作成するPDFのファイル名に使う引数
Sub printPDF(ByVal printSheet As String, ByVal customerName As String)

    
    pdfName = Format(Now(), "yyyymmdd_hhmm") & "_" & customerName
    
    MsgBox pdfName
    
    Dim pathName As String
    
    pathName = ThisWorkbook.Worksheets("menu").Range("A12").Text
    
    
    'ChDir "C:\MyDoc\plan" 'カレントフォルダの指定。ここで指定したフォルダにPDFが保存されます。
    
    ChDir pathName
    
    fileSaveName = Application.GetSaveAsFilename(pdfName, _
    fileFilter:="PDF Files (*.pdf), *.pdf")
    
    If fileSaveName <> False Then
     ' 印刷設定の指定
     With Sheets(printSheet).PageSetup
                    .Orientation = xlPortrait ' 縦向き印刷
                   '.Orientation = xlLandscape '横向き印刷
                    .FitToPagesWide = 1 '横幅を印刷用紙にフィットさせる
                    .FitToPagesTall = 1 '縦幅を印刷用紙にフィットさせる
                    .Zoom = False ' 拡大しない
                    .PrintArea = "B$1:$H$24" '印刷範囲の指定
                    '.PageSize = xlPaperA4 '印刷用紙サイズの指定
                 
     End With
    
      Sheets(printSheet).ExportAsFixedFormat Type:=xlTypePDF, _
        FileName:=fileSaveName, _
        Quality:=xlQualityStandard, _
        IncludeDocProperties:=True, _
        IgnorePrintAreas:=False, _
        OpenAfterPublish:=True
        
    End If
    'MsgBox "PDF File Saved to" & " " & fileSaveName
    
End Sub







