# Clear previous logs and test login
Write-Host "=== Testing login with monitoring ==="

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
    
    # Parse JSON response
    $loginResult = $loginResponse.Content | ConvertFrom-Json
    if ($loginResult.code -eq 200) {
        Write-Host "Login successful!"
    } else {
        Write-Host "Login failed: $($loginResult.msg)"
    }
} catch {
    Write-Host "Login error: $($_.Exception.Message)"
    if ($_.Exception.Response) {
        $reader = New-Object System.IO.StreamReader($_.Exception.Response.GetResponseStream())
        $responseBody = $reader.ReadToEnd()
        Write-Host "Error response body: $responseBody"
    }
}

Write-Host "\n=== Backend logs after login attempt ==="
