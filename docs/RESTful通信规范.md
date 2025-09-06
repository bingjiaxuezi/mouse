# 实验驱动小鼠管理系统 RESTful 通信规范

## 1. 通信架构概述

### 1.1 系统通信拓扑
```
┌─────────────────┐    HTTP/WebSocket    ┌─────────────────┐
│   Python采集端   │ ◄─────────────────► │ Spring Boot后端  │
│                 │                      │                 │
│ • 视频采集      │                      │ • 数据接收      │
│ • 行为识别      │                      │ • 数据存储      │
│ • 数据上报      │                      │ • 业务逻辑      │
│ • 状态监控      │                      │ • 用户管理      │
└─────────────────┘                      └─────────────────┘
                                                   ▲
                                                   │ HTTP
                                                   ▼
                                         ┌─────────────────┐
                                         │   前端应用      │
                                         │                 │
                                         │ • Vue.js Web    │
                                         │ • uni-app移动端 │
                                         └─────────────────┘
```

### 1.2 通信协议选择
- **主要协议**: HTTP/HTTPS (RESTful API)
- **实时通信**: WebSocket (行为数据实时推送)
- **数据格式**: JSON
- **认证方式**: JWT Token + API Key

## 2. 认证与安全机制

### 2.1 设备注册与认证

#### 设备注册流程
```
Python采集端                    Spring Boot后端
     │                              │
     │ POST /api/device/register    │
     │ ─────────────────────────► │
     │                              │
     │ ◄───────────────────────── │
     │ 200 OK + device_token       │
     │                              │
```

#### 注册接口定义
```http
POST /api/device/register
Content-Type: application/json

{
  "device_name": "采集端-实验室A-001",
  "device_type": "video_capture",
  "location": "实验室A",
  "mac_address": "00:1B:44:11:3A:B7",
  "capabilities": [
    "video_capture",
    "behavior_analysis",
    "real_time_processing"
  ],
  "hardware_info": {
    "cpu": "Intel i7-12700K",
    "gpu": "NVIDIA RTX 3080",
    "memory": "32GB",
    "camera": "Hikvision DS-2CD2385G1"
  }
}
```

#### 注册响应
```json
{
  "code": 200,
  "message": "设备注册成功",
  "data": {
    "device_id": "dev_20241201_001",
    "device_token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
    "api_key": "mk_live_51Tq8XKWnKKQn7D8QnVrjMLjXitOakvC",
    "server_endpoints": {
      "data_upload": "/api/behavior/upload",
      "heartbeat": "/api/device/heartbeat",
      "config_sync": "/api/device/config"
    },
    "config": {
      "upload_interval": 5,
      "batch_size": 100,
      "compression_enabled": true,
      "retry_attempts": 3
    }
  }
}
```

### 2.2 请求认证机制

#### 认证头部
```http
Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...
X-API-Key: mk_live_51Tq8XKWnKKQn7D8QnVrjMLjXitOakvC
X-Device-ID: dev_20241201_001
X-Timestamp: 1701234567890
X-Signature: sha256=a8b7c6d5e4f3g2h1...
```

#### 签名算法
```python
import hmac
import hashlib
import time

def generate_signature(api_key, device_id, timestamp, body):
    """
    生成请求签名
    """
    message = f"{device_id}:{timestamp}:{body}"
    signature = hmac.new(
        api_key.encode('utf-8'),
        message.encode('utf-8'),
        hashlib.sha256
    ).hexdigest()
    return f"sha256={signature}"
```

## 3. 核心API接口定义

### 3.1 行为数据上报接口

#### 批量上报接口
```http
POST /api/behavior/batch
Authorization: Bearer {device_token}
X-API-Key: {api_key}
X-Device-ID: {device_id}
Content-Type: application/json

{
  "batch_id": "batch_20241201_143022_001",
  "device_id": "dev_20241201_001",
  "cage_id": "cage_001",
  "timestamp": 1701234567890,
  "data": [
    {
      "mouse_id": "mouse_001",
      "timestamp": 1701234567890,
      "position": {
        "x": 125.5,
        "y": 89.3,
        "confidence": 0.95
      },
      "behaviors": {
        "sleeping": {
          "detected": true,
          "confidence": 0.92,
          "duration": 1800
        },
        "grooming": {
          "detected": false,
          "confidence": 0.15
        },
        "scratching": {
          "detected": false,
          "confidence": 0.08
        },
        "twitching": {
          "detected": false,
          "confidence": 0.03
        },
        "defecating": {
          "detected": false,
          "confidence": 0.12
        }
      },
      "motion_data": {
        "velocity": 0.02,
        "acceleration": 0.001,
        "direction": 45.5
      },
      "environmental_data": {
        "temperature": 22.5,
        "humidity": 65.2,
        "light_intensity": 120.8
      }
    }
  ]
}
```

#### 响应格式
```json
{
  "code": 200,
  "message": "数据上报成功",
  "data": {
    "batch_id": "batch_20241201_143022_001",
    "processed_count": 1,
    "failed_count": 0,
    "next_upload_time": 1701234572890
  }
}
```

### 3.2 设备心跳接口

```http
POST /api/device/heartbeat
Authorization: Bearer {device_token}
X-Device-ID: {device_id}

{
  "device_id": "dev_20241201_001",
  "timestamp": 1701234567890,
  "status": "online",
  "system_info": {
    "cpu_usage": 45.2,
    "memory_usage": 68.7,
    "gpu_usage": 78.3,
    "disk_usage": 23.1,
    "temperature": 65.5
  },
  "processing_stats": {
    "fps": 29.8,
    "detection_count": 5,
    "tracking_accuracy": 0.94,
    "behavior_detection_rate": 0.87
  },
  "error_logs": [
    {
      "timestamp": 1701234560000,
      "level": "WARNING",
      "message": "检测到小鼠ID切换，已自动修正"
    }
  ]
}
```

### 3.3 配置同步接口

```http
GET /api/device/config/{device_id}
Authorization: Bearer {device_token}

Response:
{
  "code": 200,
  "data": {
    "detection_config": {
      "confidence_threshold": 0.5,
      "iou_threshold": 0.4,
      "max_tracking_age": 50,
      "min_detection_size": 20
    },
    "behavior_config": {
      "sleep_motion_threshold": 0.1,
      "grooming_duration_min": 5,
      "twitching_variance_threshold": 50
    },
    "upload_config": {
      "batch_size": 100,
      "upload_interval": 5,
      "compression_enabled": true,
      "retry_attempts": 3
    }
  }
}
```

### 3.4 实验管理接口（核心优先级）

#### 3.4.1 创建实验项目
```http
POST /api/experiment/create
Authorization: Bearer {admin_token}
Content-Type: application/json

{
  "experiment_name": "小鼠行为学实验A组",
  "experiment_type": "behavior_analysis",
  "template_id": "template_001",
  "start_date": "2024-12-01",
  "end_date": "2024-12-31",
  "description": "研究小鼠在不同环境下的行为模式",
  "researcher_id": "researcher_001",
  "institution": "生物医学研究所"
}

Response:
{
  "code": 200,
  "message": "实验创建成功",
  "data": {
    "experiment_id": "exp_20241201_001",
    "experiment_name": "小鼠行为学实验A组",
    "status": "created",
    "created_time": "2024-12-01T14:30:22Z"
  }
}
```

#### 3.4.2 启动实验
```http
POST /api/experiment/{experiment_id}/start
Authorization: Bearer {admin_token}

{
  "start_time": "2024-12-01T09:00:00Z",
  "config_overrides": {
    "detection_sensitivity": 0.8,
    "recording_duration": 3600
  }
}
```

#### 3.4.3 绑定设备到实验
```http
POST /api/experiment/{experiment_id}/bind-device
Authorization: Bearer {admin_token}

{
  "device_id": "dev_20241201_001",
  "binding_type": "primary_capture",
  "location": "实验室A-区域1"
}
```

### 3.5 实验二维码管理接口

#### 3.5.1 生成实验二维码
```http
POST /api/experiment/qrcode/generate
Authorization: Bearer {admin_token}
Content-Type: application/json

{
  "experiment_id": "exp_20241201_001",
  "cage_id": "cage_001",
  "qr_type": "experiment_cage_binding",
  "algorithm": "QR_CODE",
  "error_correction": "M",
  "size": 200,
  "expire_days": 365,
  "description": "实验exp_20241201_001的笼子001二维码"
}

Response:
{
  "code": 200,
  "message": "实验二维码生成成功",
  "data": {
    "qr_id": "qr_exp_20241201_001",
    "experiment_id": "exp_20241201_001",
    "cage_id": "cage_001",
    "qr_content": "EXP:exp_20241201_001:CAGE:cage_001:20241201:001",
    "qr_code_base64": "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAA...",
    "qr_code_url": "/api/experiment/qrcode/image/qr_exp_20241201_001",
    "expire_time": "2025-12-01T00:00:00Z",
    "created_time": "2024-12-01T14:30:22Z",
    "binding_status": "ready"
  }
}
```

#### 3.5.2 扫描实验二维码
```http
POST /api/experiment/qrcode/scan
Authorization: Bearer {device_token}
X-Device-ID: {device_id}

{
  "qr_content": "EXP:exp_20241201_001:CAGE:cage_001:20241201:001",
  "scan_time": "2024-12-01T14:35:22Z",
  "device_location": "实验室A-区域1"
}

Response:
{
  "code": 200,
  "message": "实验二维码扫描成功",
  "data": {
    "experiment_id": "exp_20241201_001",
    "cage_id": "cage_001",
    "binding_confirmed": true,
    "experiment_config": {
      "detection_sensitivity": 0.8,
      "recording_duration": 3600,
      "behavior_types": ["sleeping", "grooming", "scratching", "twitching"]
    }
  }
}
```

#### 3.4.2 扫描二维码
```http
POST /api/qrcode/scan
Authorization: Bearer {device_token}
Content-Type: application/json

{
  "qr_content": "CAGE:cage_001:20241201:001",
  "device_id": "dev_20241201_001",
  "scan_location": "实验室A-区域1",
  "timestamp": 1701234567890
}

Response:
{
  "code": 200,
  "message": "二维码扫描成功",
  "data": {
    "scan_result": "success",
    "cage_info": {
      "cage_id": "cage_001",
      "cage_name": "笼子001",
      "location": "实验室A-区域1",
      "capacity": 5,
      "current_mice_count": 3,
      "status": "active"
    },
    "binding_result": {
      "device_id": "dev_20241201_001",
      "cage_id": "cage_001",
      "binding_time": "2024-12-01T14:30:22Z",
      "binding_status": "success"
    }
  }
}
```

#### 3.4.3 获取二维码图片
```http
GET /api/qrcode/image/{qr_id}

Response:
- Content-Type: image/png
- 返回二维码PNG图片数据
```

#### 3.4.4 查询二维码信息
```http
GET /api/qrcode/info/{qr_id}
Authorization: Bearer {token}

Response:
{
  "code": 200,
  "data": {
    "qr_id": "qr_20241201_001",
    "cage_id": "cage_001",
    "qr_content": "CAGE:cage_001:20241201:001",
    "status": "active",
    "scan_count": 15,
    "last_scan_time": "2024-12-01T14:25:30Z",
    "last_scan_device": "dev_20241201_001",
    "created_time": "2024-12-01T10:00:00Z",
    "expire_time": "2025-12-01T00:00:00Z"
  }
}
```

### 3.5 笼子管理接口

#### 3.5.1 设备绑定笼子
```http
POST /api/cage/bind
Authorization: Bearer {device_token}
Content-Type: application/json

{
  "device_id": "dev_20241201_001",
  "cage_id": "cage_001",
  "binding_method": "qr_scan",
  "qr_content": "CAGE:cage_001:20241201:001",
  "location": "实验室A-区域1"
}

Response:
{
  "code": 200,
  "message": "设备绑定笼子成功",
  "data": {
    "binding_id": "bind_20241201_001",
    "device_id": "dev_20241201_001",
    "cage_id": "cage_001",
    "binding_time": "2024-12-01T14:30:22Z",
    "status": "active"
  }
}
```

#### 3.5.2 获取笼子状态
```http
GET /api/cage/status/{cage_id}
Authorization: Bearer {token}

Response:
{
  "code": 200,
  "data": {
    "cage_id": "cage_001",
    "cage_name": "笼子001",
    "location": "实验室A-区域1",
    "capacity": 5,
    "current_mice_count": 3,
    "status": "active",
    "bound_device": "dev_20241201_001",
    "last_activity": "2024-12-01T14:25:30Z",
    "mice_list": [
      {
        "mouse_id": "mouse_001",
        "mouse_name": "小鼠001",
        "status": "active",
        "last_detected": "2024-12-01T14:25:30Z"
      }
    ]
  }
}
```

## 4. WebSocket实时通信

### 4.1 连接建立
```javascript
// 前端连接示例
const ws = new WebSocket('ws://localhost:8080/ws/behavior');

// 连接认证
ws.onopen = function() {
    ws.send(JSON.stringify({
        type: 'auth',
        token: 'eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...',
        device_id: 'dev_20241201_001'
    }));
};
```

### 4.2 实时数据推送格式

#### 4.2.1 行为数据推送
```json
{
  "type": "behavior_update",
  "timestamp": 1701234567890,
  "device_id": "dev_20241201_001",
  "cage_id": "cage_001",
  "data": {
    "mouse_id": "mouse_001",
    "current_behavior": "grooming",
    "confidence": 0.89,
    "position": {"x": 125.5, "y": 89.3},
    "duration": 45
  }
}
```

#### 4.2.2 设备状态推送
```json
{
  "type": "device_status",
  "timestamp": 1701234567890,
  "device_id": "dev_20241201_001",
  "data": {
    "status": "online",
    "cage_binding": {
      "cage_id": "cage_001",
      "binding_time": "2024-12-01T14:30:22Z",
      "binding_method": "qr_scan"
    },
    "system_info": {
      "cpu_usage": 45.2,
      "memory_usage": 68.7,
      "fps": 29.8
    }
  }
}
```

#### 4.2.3 二维码扫描推送
```json
{
  "type": "qr_scan_event",
  "timestamp": 1701234567890,
  "device_id": "dev_20241201_001",
  "data": {
    "qr_id": "qr_20241201_001",
    "cage_id": "cage_001",
    "scan_result": "success",
    "scan_location": "实验室A-区域1",
    "binding_status": "success"
  }
}
```

#### 4.2.4 系统告警推送
```json
{
  "type": "system_alert",
  "timestamp": 1701234567890,
  "device_id": "dev_20241201_001",
  "cage_id": "cage_001",
  "data": {
    "alert_type": "mouse_missing",
    "severity": "warning",
    "message": "检测到小鼠mouse_001可能离开监控区域",
    "mouse_id": "mouse_001",
    "last_position": {"x": 195.5, "y": 198.3},
    "missing_duration": 300
  }
}
```

## 5. 错误处理与重试机制

### 5.1 错误码定义
```json
{
  "1000": "成功",
  "4001": "认证失败",
  "4003": "设备未注册",
  "4004": "设备已禁用",
  "4005": "API密钥无效",
  "4006": "签名验证失败",
  "4007": "请求过期",
  "4008": "权限不足",
  "4009": "资源不存在",
  "4010": "二维码无效",
  "4011": "二维码已过期",
  "4012": "二维码已被使用",
  "4013": "笼子已被绑定",
  "4014": "设备已绑定其他笼子",
  "4015": "笼子不存在",
  "4016": "设备离线",
  "5001": "数据格式错误",
  "5002": "数据验证失败",
  "5003": "存储失败",
  "5004": "服务器内部错误",
  "5005": "二维码生成失败",
  "5006": "图片处理失败",
  "5007": "数据库连接失败",
  "5008": "外部服务调用失败"
}
```

### 5.2 重试策略
```python
import time
import requests
from typing import Optional

class APIClient:
    def __init__(self, base_url: str, device_token: str, api_key: str):
        self.base_url = base_url
        self.device_token = device_token
        self.api_key = api_key
        self.max_retries = 3
        self.retry_delays = [1, 2, 4]  # 指数退避
    
    def upload_data(self, data: dict) -> Optional[dict]:
        """
        上报数据，带重试机制
        """
        for attempt in range(self.max_retries):
            try:
                response = self._make_request('/api/behavior/batch', data)
                if response.status_code == 200:
                    return response.json()
                elif response.status_code in [500, 502, 503, 504]:
                    # 服务器错误，可以重试
                    if attempt < self.max_retries - 1:
                        time.sleep(self.retry_delays[attempt])
                        continue
                else:
                    # 客户端错误，不重试
                    break
            except requests.exceptions.RequestException as e:
                if attempt < self.max_retries - 1:
                    time.sleep(self.retry_delays[attempt])
                    continue
                raise e
        
        return None
```

## 6. 数据模拟与测试

### 6.1 假数据生成器
```python
import random
import time
from datetime import datetime, timedelta

class MockDataGenerator:
    def __init__(self):
        self.mouse_ids = [f"mouse_{i:03d}" for i in range(1, 6)]
        self.behaviors = ['sleeping', 'grooming', 'scratching', 'twitching', 'defecating']
    
    def generate_behavior_data(self, count: int = 100) -> list:
        """
        生成模拟行为数据
        """
        data = []
        base_time = int(time.time()) - 3600  # 从1小时前开始
        
        for i in range(count):
            timestamp = base_time + i * 5  # 每5秒一条数据
            
            for mouse_id in self.mouse_ids:
                # 模拟小鼠位置（在200x200的区域内）
                position = {
                    'x': random.uniform(10, 190),
                    'y': random.uniform(10, 190),
                    'confidence': random.uniform(0.8, 0.99)
                }
                
                # 模拟行为检测结果
                behaviors = {}
                active_behavior = random.choice(self.behaviors + [None, None, None])  # 大部分时间无特殊行为
                
                for behavior in self.behaviors:
                    if behavior == active_behavior:
                        behaviors[behavior] = {
                            'detected': True,
                            'confidence': random.uniform(0.7, 0.95),
                            'duration': random.randint(5, 300)
                        }
                    else:
                        behaviors[behavior] = {
                            'detected': False,
                            'confidence': random.uniform(0.01, 0.3)
                        }
                
                # 模拟运动数据
                motion_data = {
                    'velocity': random.uniform(0, 5.0) if active_behavior != 'sleeping' else random.uniform(0, 0.2),
                    'acceleration': random.uniform(-1, 1),
                    'direction': random.uniform(0, 360)
                }
                
                # 模拟环境数据
                environmental_data = {
                    'temperature': random.uniform(20, 25),
                    'humidity': random.uniform(50, 70),
                    'light_intensity': random.uniform(50, 200)
                }
                
                data.append({
                    'mouse_id': mouse_id,
                    'timestamp': timestamp * 1000,  # 转换为毫秒
                    'position': position,
                    'behaviors': behaviors,
                    'motion_data': motion_data,
                    'environmental_data': environmental_data
                })
        
        return data
    
    def generate_batch_request(self, data_count: int = 50) -> dict:
        """
        生成批量上报请求
        """
        return {
            'batch_id': f"batch_{datetime.now().strftime('%Y%m%d_%H%M%S')}_{random.randint(1000, 9999)}",
            'device_id': 'dev_mock_001',
            'cage_id': 'cage_001',
            'timestamp': int(time.time() * 1000),
            'data': self.generate_behavior_data(data_count)
        }
```

### 6.2 API测试脚本
```python
import requests
import json
from mock_data_generator import MockDataGenerator

class APITester:
    def __init__(self, base_url: str = 'http://localhost:8080'):
        self.base_url = base_url
        self.generator = MockDataGenerator()
        self.device_token = None
        self.api_key = None
        self.admin_token = None
        self.qr_id = None
    
    def test_device_registration(self):
        """
        测试设备注册
        """
        url = f"{self.base_url}/api/device/register"
        data = {
            'device_name': '测试采集端-001',
            'device_type': 'video_capture',
            'location': '测试实验室',
            'mac_address': '00:1B:44:11:3A:B7',
            'capabilities': ['video_capture', 'behavior_analysis'],
            'hardware_info': {
                'cpu': 'Intel i7-12700K',
                'gpu': 'NVIDIA RTX 3080',
                'memory': '32GB'
            }
        }
        
        response = requests.post(url, json=data)
        if response.status_code == 200:
            result = response.json()
            self.device_token = result['data']['device_token']
            self.api_key = result['data']['api_key']
            print("设备注册成功")
            return True
        else:
            print(f"设备注册失败: {response.text}")
            return False
    
    def test_qr_code_generation(self):
        """
        测试二维码生成
        """
        if not self.admin_token:
            print("需要管理员权限")
            return False
        
        url = f"{self.base_url}/api/qrcode/generate"
        headers = {
            'Authorization': f'Bearer {self.admin_token}',
            'Content-Type': 'application/json'
        }
        
        data = {
            'cage_id': 'cage_test_001',
            'qr_type': 'cage_binding',
            'algorithm': 'QR_CODE',
            'error_correction': 'M',
            'size': 200,
            'expire_days': 365,
            'description': '测试笼子的二维码'
        }
        
        response = requests.post(url, headers=headers, json=data)
        if response.status_code == 200:
            result = response.json()
            self.qr_id = result['data']['qr_id']
            print(f"二维码生成成功: {self.qr_id}")
            return True
        else:
            print(f"二维码生成失败: {response.text}")
            return False
    
    def test_qr_code_scan(self):
        """
        测试二维码扫描
        """
        if not self.device_token or not self.qr_id:
            print("请先完成设备注册和二维码生成")
            return False
        
        url = f"{self.base_url}/api/qrcode/scan"
        headers = {
            'Authorization': f'Bearer {self.device_token}',
            'Content-Type': 'application/json'
        }
        
        data = {
            'qr_content': 'CAGE:cage_test_001:20241201:001',
            'device_id': 'dev_mock_001',
            'scan_location': '测试实验室',
            'timestamp': int(time.time() * 1000)
        }
        
        response = requests.post(url, headers=headers, json=data)
        if response.status_code == 200:
            print("二维码扫描成功")
            return True
        else:
            print(f"二维码扫描失败: {response.text}")
            return False
    
    def test_cage_binding(self):
        """
        测试设备绑定笼子
        """
        if not self.device_token:
            print("请先完成设备注册")
            return False
        
        url = f"{self.base_url}/api/cage/bind"
        headers = {
            'Authorization': f'Bearer {self.device_token}',
            'Content-Type': 'application/json'
        }
        
        data = {
            'device_id': 'dev_mock_001',
            'cage_id': 'cage_test_001',
            'binding_method': 'qr_scan',
            'qr_content': 'CAGE:cage_test_001:20241201:001',
            'location': '测试实验室'
        }
        
        response = requests.post(url, headers=headers, json=data)
        if response.status_code == 200:
            print("设备绑定笼子成功")
            return True
        else:
            print(f"设备绑定笼子失败: {response.text}")
            return False
    
    def test_behavior_upload(self):
        """
        测试行为数据上报
        """
        if not self.device_token:
            print("请先完成设备注册")
            return False
        
        url = f"{self.base_url}/api/behavior/batch"
        headers = {
            'Authorization': f'Bearer {self.device_token}',
            'X-API-Key': self.api_key,
            'X-Device-ID': 'dev_mock_001',
            'Content-Type': 'application/json'
        }
        
        data = self.generator.generate_batch_request(10)
        
        response = requests.post(url, headers=headers, json=data)
        if response.status_code == 200:
            print("数据上报成功")
            return True
        else:
            print(f"数据上报失败: {response.text}")
            return False
    
    def run_all_tests(self):
        """
        运行所有测试
        """
        print("开始API测试...")
        
        # 基础功能测试
        if self.test_device_registration():
            # 二维码功能测试（需要管理员权限）
            if self.admin_token:
                if self.test_qr_code_generation():
                    self.test_qr_code_scan()
                    self.test_cage_binding()
            
            # 数据上报测试
            self.test_behavior_upload()
        
        print("测试完成")
    
    def set_admin_token(self, token: str):
        """
        设置管理员令牌
        """
        self.admin_token = token

if __name__ == '__main__':
    tester = APITester()
    tester.run_all_tests()
```

## 7. 性能优化建议

### 7.1 数据压缩
```python
import gzip
import json

def compress_data(data: dict) -> bytes:
    """
    压缩JSON数据
    """
    json_str = json.dumps(data, separators=(',', ':'))
    return gzip.compress(json_str.encode('utf-8'))

def decompress_data(compressed_data: bytes) -> dict:
    """
    解压缩数据
    """
    json_str = gzip.decompress(compressed_data).decode('utf-8')
    return json.loads(json_str)
```

### 7.2 批量处理
- 建议批量大小：50-100条记录
- 上报间隔：5-10秒
- 压缩传输：启用gzip压缩
- 连接复用：使用HTTP/1.1 Keep-Alive

### 7.3 缓存策略
- 配置信息缓存：本地缓存30分钟
- 失败数据缓存：本地存储，定期重试
- 网络状态检测：自动切换在线/离线模式
- 二维码信息缓存：本地缓存2小时，减少重复查询
- 笼子状态缓存：本地缓存10分钟，提高响应速度

### 7.4 二维码优化
```python
import qrcode
from PIL import Image
import io
import base64

class QRCodeOptimizer:
    def __init__(self):
        self.cache = {}  # 二维码缓存
    
    def generate_optimized_qr(self, content: str, size: int = 200) -> str:
        """
        生成优化的二维码
        """
        cache_key = f"{content}_{size}"
        if cache_key in self.cache:
            return self.cache[cache_key]
        
        # 创建二维码实例
        qr = qrcode.QRCode(
            version=1,
            error_correction=qrcode.constants.ERROR_CORRECT_M,
            box_size=size//25,  # 动态计算box_size
            border=2,
        )
        
        qr.add_data(content)
        qr.make(fit=True)
        
        # 生成图片
        img = qr.make_image(fill_color="black", back_color="white")
        
        # 转换为base64
        buffer = io.BytesIO()
        img.save(buffer, format='PNG', optimize=True)
        img_str = base64.b64encode(buffer.getvalue()).decode()
        
        # 缓存结果
        self.cache[cache_key] = f"data:image/png;base64,{img_str}"
        return self.cache[cache_key]
    
    def batch_generate_qr(self, contents: list, size: int = 200) -> dict:
        """
        批量生成二维码
        """
        results = {}
        for content in contents:
            results[content] = self.generate_optimized_qr(content, size)
        return results
```

### 7.5 连接池优化
```python
import requests
from requests.adapters import HTTPAdapter
from urllib3.util.retry import Retry

class OptimizedAPIClient:
    def __init__(self, base_url: str):
        self.base_url = base_url
        self.session = requests.Session()
        
        # 配置重试策略
        retry_strategy = Retry(
            total=3,
            backoff_factor=1,
            status_forcelist=[429, 500, 502, 503, 504],
        )
        
        # 配置连接池
        adapter = HTTPAdapter(
            pool_connections=10,
            pool_maxsize=20,
            max_retries=retry_strategy
        )
        
        self.session.mount("http://", adapter)
        self.session.mount("https://", adapter)
    
    def upload_with_pool(self, data: dict) -> dict:
        """
        使用连接池上传数据
        """
        response = self.session.post(
            f"{self.base_url}/api/behavior/batch",
            json=data,
            timeout=(5, 30)  # 连接超时5秒，读取超时30秒
        )
        return response.json()
```

## 8. 监控与日志

### 8.1 关键指标监控

#### 8.1.1 API性能指标
- API响应时间（P50、P95、P99）
- 数据上报成功率
- 设备在线状态
- 错误率统计
- 并发连接数

#### 8.1.2 二维码相关指标
- 二维码生成成功率
- 二维码扫描成功率
- 设备绑定成功率
- 二维码过期率
- 扫描频次统计

#### 8.1.3 业务指标
- 活跃设备数量
- 笼子绑定率
- 数据上报频率
- 异常告警数量

### 8.2 日志格式

#### 8.2.1 行为数据上报日志
```json
{
  "timestamp": "2024-12-01T14:30:22.890Z",
  "level": "INFO",
  "service": "mouse-monitor-api",
  "device_id": "dev_20241201_001",
  "cage_id": "cage_001",
  "request_id": "req_1701234567890_001",
  "message": "行为数据上报成功",
  "data": {
    "batch_id": "batch_20241201_143022_001",
    "record_count": 50,
    "processing_time": 125,
    "data_size": 2048
  }
}
```

#### 8.2.2 二维码操作日志
```json
{
  "timestamp": "2024-12-01T14:30:22.890Z",
  "level": "INFO",
  "service": "qrcode-service",
  "operation": "qr_scan",
  "request_id": "req_1701234567890_002",
  "message": "二维码扫描成功",
  "data": {
    "qr_id": "qr_20241201_001",
    "cage_id": "cage_001",
    "device_id": "dev_20241201_001",
    "scan_location": "实验室A-区域1",
    "scan_result": "success",
    "processing_time": 45
  }
}
```

#### 8.2.3 设备绑定日志
```json
{
  "timestamp": "2024-12-01T14:30:22.890Z",
  "level": "INFO",
  "service": "device-management",
  "operation": "cage_binding",
  "request_id": "req_1701234567890_003",
  "message": "设备绑定笼子成功",
  "data": {
    "device_id": "dev_20241201_001",
    "cage_id": "cage_001",
    "binding_method": "qr_scan",
    "previous_cage": null,
    "binding_duration": 150
  }
}
```

#### 8.2.4 错误日志
```json
{
  "timestamp": "2024-12-01T14:30:22.890Z",
  "level": "ERROR",
  "service": "mouse-monitor-api",
  "device_id": "dev_20241201_001",
  "request_id": "req_1701234567890_004",
  "message": "二维码扫描失败",
  "error": {
    "code": "4011",
    "type": "QR_CODE_EXPIRED",
    "description": "二维码已过期",
    "qr_id": "qr_20241130_001",
    "expire_time": "2024-11-30T23:59:59Z"
  },
  "stack_trace": "..."
}
```

### 8.3 监控告警规则

#### 8.3.1 性能告警
- API响应时间 > 5秒：WARNING
- API响应时间 > 10秒：CRITICAL
- 错误率 > 5%：WARNING
- 错误率 > 10%：CRITICAL

#### 8.3.2 业务告警
- 设备离线超过5分钟：WARNING
- 设备离线超过30分钟：CRITICAL
- 二维码扫描失败率 > 10%：WARNING
- 数据上报中断超过10分钟：CRITICAL

### 8.4 日志聚合与分析

```python
# 日志分析示例
import json
from datetime import datetime, timedelta

class LogAnalyzer:
    def __init__(self):
        self.metrics = {
            'api_calls': 0,
            'errors': 0,
            'qr_scans': 0,
            'device_bindings': 0
        }
    
    def analyze_log_line(self, log_line: str):
        """
        分析单行日志
        """
        try:
            log_data = json.loads(log_line)
            
            # 统计API调用
            if 'request_id' in log_data:
                self.metrics['api_calls'] += 1
            
            # 统计错误
            if log_data.get('level') == 'ERROR':
                self.metrics['errors'] += 1
            
            # 统计二维码扫描
            if log_data.get('operation') == 'qr_scan':
                self.metrics['qr_scans'] += 1
            
            # 统计设备绑定
            if log_data.get('operation') == 'cage_binding':
                self.metrics['device_bindings'] += 1
                
        except json.JSONDecodeError:
            pass
    
    def get_error_rate(self) -> float:
        """
        计算错误率
        """
        if self.metrics['api_calls'] == 0:
            return 0.0
        return self.metrics['errors'] / self.metrics['api_calls'] * 100
```

这套RESTful通信规范确保了Python采集端与Spring Boot后端之间的可靠、安全、高效通信，特别是在二维码管理和设备绑定方面提供了完整的解决方案，为整个计算机视觉小鼠管理系统提供了坚实的数据传输基础。