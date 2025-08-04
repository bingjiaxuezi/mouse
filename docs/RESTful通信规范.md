# 小鼠行为监测系统 RESTful 通信规范

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
  "5001": "数据格式错误",
  "5002": "数据验证失败",
  "5003": "存储失败",
  "5004": "服务器内部错误"
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
        
        if self.test_device_registration():
            self.test_behavior_upload()
        
        print("测试完成")

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

## 8. 监控与日志

### 8.1 关键指标监控
- API响应时间
- 数据上报成功率
- 设备在线状态
- 错误率统计

### 8.2 日志格式
```json
{
  "timestamp": "2024-12-01T14:30:22.890Z",
  "level": "INFO",
  "service": "mouse-monitor-api",
  "device_id": "dev_20241201_001",
  "request_id": "req_1701234567890_001",
  "message": "行为数据上报成功",
  "data": {
    "batch_id": "batch_20241201_143022_001",
    "record_count": 50,
    "processing_time": 125
  }
}
```

这套RESTful通信规范确保了Python采集端与Spring Boot后端之间的可靠、安全、高效通信，为整个小鼠行为监测系统提供了坚实的数据传输基础。