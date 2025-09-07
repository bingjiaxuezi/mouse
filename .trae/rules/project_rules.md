项目编写完成后统一使用：PowerShell -ExecutionPolicy Bypass -File "D:\mouse\scripts\deploy.ps1" 一键构造项目，项目构造完成后，在项目根目录下使用：docker-compose up -d 一键启动项目，无需其他操作


获取登录Token
$session = New-Object Microsoft.PowerShell.Commands.WebRequestSession; $loginResponse = Invoke-WebRequest -Uri "http://localhost:8080/login" -Method POST -Headers @{"Content-Type"="application/x-www-form-urlencoded"} -WebSession $session -Body "username=admin&password=admin123"; $cageResponse = Invoke-WebRequest -Uri "http://localhost:8080/system/SysCage/list" -Method POST -Headers @{"Content-Type"="application/x-www-form-urlencoded"} -WebSession $session -Body "pageSize=100&pageNum=1"; $cageResponse.Content 
