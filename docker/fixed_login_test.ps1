Write-Host "=== Testing login with captcha fields ==="

$baseUrl = "http://localhost:8080"
$session = New-Object Microsoft.PowerShell.Commands.WebRequestSession

# Get main page first
try {
    $response = Invoke-WebRequest -Uri $baseUrl -Method GET -SessionVariable session
    Write-Host "Main page status: $($response.StatusCode)"
} catch {
    Write-Host "Error getting main page: $($_.Exception.Message)"
}

# Try login with captcha fields (empty values)
$loginData = @{
    username = "admin"
    password = "admin123"
    rememberMe = $false
    code = ""
    uuid = ""
} | ConvertTo-Json

Write-Host "Login data with captcha fields: $loginData"

try {
    $loginResponse = Invoke-WebRequest -Uri "$baseUrl/login" -Method POST -Body $loginData -ContentType "application/json" -WebSession $session
    Write-Host "Login status: $($loginResponse.StatusCode)"
    Write-Host "Login response: $($loginResponse.Content)"
} catch {
    Write-Host "Login error: $($_.Exception.Message)"
    if ($_.Exception.Response) {
        $reader = New-Object System.IO.StreamReader($_.Exception.Response.GetResponseStream())
        $responseBody = $reader.ReadToEnd()
        Write-Host "Error response: $responseBody"
    }
}

# Also try with different approach - maybe the system expects form data instead of JSON
Write-Host "\n=== Trying with form data ==="
$formData = "username=admin&password=admin123&rememberMe=false"
try {
    $formResponse = Invoke-WebRequest -Uri "$baseUrl/login" -Method POST -Body $formData -ContentType "application/x-www-form-urlencoded" -WebSession $session
    Write-Host "Form login status: $($formResponse.StatusCode)"
    Write-Host "Form login response: $($formResponse.Content)"
} catch {
    Write-Host "Form login error: $($_.Exception.Message)"
    if ($_.Exception.Response) {
        $reader = New-Object System.IO.StreamReader($_.Exception.Response.GetResponseStream())
        $responseBody = $reader.ReadToEnd()
        Write-Host "Form error response: $responseBody"
    }
}
