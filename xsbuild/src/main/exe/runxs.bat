@echo off
for /f "delims=" %%a in ('java -classpath ..\lib\xscommon-1.0.0.jar com.flx.xs.common.util.ReadManifest $1-1.0.0.jar Class-Path ;') do @set XS_CLASSPATH=%%a
for /f "delims=" %%b in ('java -classpath ..\lib\xscommon-1.0.0.jar com.flx.xs.common.util.ReadManifest $1-1.0.0.jar Main-Class') do @set XS_MAIN=%%b
echo CLASSPATH is %XS_CLASSPATH%
echo MAIN is %XS_MAIN%