cd /d e:\view
set yyyy=%date:~0,4%
set mm=%date:~5,2%
set dd=%date:~8,2%
set time2=%time: =0%
set time3=%time2:~0,2%%time2:~3,2%%time2:~6,2%

ren SimpleView.txt SimpleView_%yyyy%%mm%%dd%_%time3%.txt

explorer.exe "e:\view"
exit
