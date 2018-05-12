del /S *.g3dj
del /S *.g3db
forfiles /s /m *.fbx /c "cmd /c  fbx-conv-win32.exe -o g3dj @path"
forfiles /s /m *.fbx /c "cmd /c  fbx-conv-win32.exe -o g3db @path"