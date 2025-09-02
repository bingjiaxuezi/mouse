# Test login process
$baseUrl = "http://localhost:8080"
$session = New-Object Microsoft.PowerShell.Commands.WebRequestSession

Write-Host "Step 1: Getting main page..."
try {
    $response = Invoke-WebRequest -Uri $baseUrl -Method GET -SessionVariable session
    Write-Host "Main page status: $($response.StatusCode)"
    Write-Host "Cookies received: $($session.Cookies.Count)"
} catch {
    Write-Host "Error getting main page: $($_.Exception.Message)"
    exit 1
}

Write-Host "\nStep 2: Attempting login..."
$loginData = @{
    username = "admin"
    password = "admin123"
    rememberMe = $false
} | ConvertTo-Json

Write-Host "Login request body: $loginData"

try {
    $loginResponse = Invoke-WebRequest -Uri "$baseUrl/login" -Method POST -Body $loginData -ContentType "application/json" -WebSession $session
    Write-Host "Login status code: $($loginResponse.StatusCode)"
    Write-Host "Login response content: $($loginResponse.Content)"
    Write-Host "Cookies after login: $($session.Cookies.Count)"
} catch {
    Write-Host "Login error: $($_.Exception.Message)"
    if ($_.Exception.Response) {
        $reader = New-Object System.IO.StreamReader($_.Exception.Response.GetResponseStream())
        $responseBody = $reader.ReadToEnd()
        Write-Host "Error response body: $responseBody"
    }
}

Write-Host "\nStep 3: Testing API access..."
try {
    $apiResponse = Invoke-WebRequest -Uri "$baseUrl/system/behavior/list" -Method GET -WebSession $session
    Write-Host "API status code: $($apiResponse.StatusCode)"
    Write-Host "API response (first 200 chars): $($apiResponse.Content.Substring(0, [Math]::Min(200, $apiResponse.Content.Length)))"
} catch {
    Write-Host "API error: $($_.Exception.Message)"
    if ($_.Exception.Response) {
        $reader = New-Object System.IO.StreamReader($_.Exception.Response.GetResponseStream())
        $responseBody = $reader.ReadToEnd()
        Write-Host "API error response: $responseBody"
    }
}
