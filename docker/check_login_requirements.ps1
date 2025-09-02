Write-Host "=== Analyzing login requirements ==="

$baseUrl = "http://localhost:8080"
$session = New-Object Microsoft.PowerShell.Commands.WebRequestSession

# Get the login page to see what fields are required
try {
    $loginPageResponse = Invoke-WebRequest -Uri "$baseUrl/login" -Method GET -SessionVariable session
    Write-Host "Login page status: $($loginPageResponse.StatusCode)"
    
    # Look for form fields or CSRF tokens
    if ($loginPageResponse.Content -match "name=\"([^\"]+)\"" -or $loginPageResponse.Content -match "captcha" -or $loginPageResponse.Content -match "uuid") {
        Write-Host "Found potential required fields in login page"
        # Extract first 1000 characters to see form structure
        $content = $loginPageResponse.Content
        if ($content.Length -gt 1000) {
            $content = $content.Substring(0, 1000)
        }
        Write-Host "Login page content (first 1000 chars): $content"
    }
} catch {
    Write-Host "Error getting login page: $($_.Exception.Message)"
}

Write-Host "\n=== Testing login with captcha fields ==="

# Try login with additional fields that might be required
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
