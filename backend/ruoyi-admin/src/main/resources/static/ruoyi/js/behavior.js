/**
 * 行为管理相关的JavaScript函数
 */

// 行为类型映射
var behaviorTypeMap = {
    'EATING': '进食',
    'DRINKING': '饮水',
    'RUNNING': '跑动',
    'RESTING': '休息',
    'GROOMING': '梳理',
    'EXPLORING': '探索',
    'SOCIAL': '社交',
    'OTHER': '其他'
};

// 行为强度映射
var intensityMap = {
    'LOW': '低',
    'MEDIUM': '中',
    'HIGH': '高'
};

/**
 * 格式化行为类型显示
 */
function formatBehaviorType(value) {
    return behaviorTypeMap[value] || value || '-';
}

/**
 * 格式化行为强度显示
 */
function formatIntensity(value) {
    var text = intensityMap[value] || value || '-';
    var className = '';
    switch(value) {
        case 'LOW':
            className = 'label-success';
            break;
        case 'MEDIUM':
            className = 'label-warning';
            break;
        case 'HIGH':
            className = 'label-danger';
            break;
        default:
            className = 'label-default';
    }
    return '<span class="label ' + className + '">' + text + '</span>';
}

/**
 * 格式化持续时间显示
 */
function formatDuration(seconds) {
    if (!seconds || seconds <= 0) {
        return '-';
    }
    
    if (seconds < 60) {
        return seconds + '秒';
    } else if (seconds < 3600) {
        var minutes = Math.floor(seconds / 60);
        var remainSeconds = seconds % 60;
        return minutes + '分' + (remainSeconds > 0 ? remainSeconds + '秒' : '');
    } else {
        var hours = Math.floor(seconds / 3600);
        var remainMinutes = Math.floor((seconds % 3600) / 60);
        return hours + '小时' + (remainMinutes > 0 ? remainMinutes + '分' : '');
    }
}

/**
 * 格式化日期时间显示
 */
function formatDateTime(dateTimeStr) {
    if (!dateTimeStr) {
        return '-';
    }
    
    var date = new Date(dateTimeStr);
    if (isNaN(date.getTime())) {
        return dateTimeStr;
    }
    
    var year = date.getFullYear();
    var month = String(date.getMonth() + 1).padStart(2, '0');
    var day = String(date.getDate()).padStart(2, '0');
    var hours = String(date.getHours()).padStart(2, '0');
    var minutes = String(date.getMinutes()).padStart(2, '0');
    var seconds = String(date.getSeconds()).padStart(2, '0');
    
    return year + '-' + month + '-' + day + ' ' + hours + ':' + minutes + ':' + seconds;
}

/**
 * 格式化日期显示（仅日期部分）
 */
function formatDate(dateStr) {
    if (!dateStr) {
        return '-';
    }
    
    var date = new Date(dateStr);
    if (isNaN(date.getTime())) {
        return dateStr;
    }
    
    var year = date.getFullYear();
    var month = String(date.getMonth() + 1).padStart(2, '0');
    var day = String(date.getDate()).padStart(2, '0');
    
    return year + '-' + month + '-' + day;
}

/**
 * 加载行为类型选项
 */
function loadBehaviorTypes(selectId, selectedValue) {
    var select = $('#' + selectId);
    select.empty();
    select.append('<option value="">请选择行为类型</option>');
    
    $.ajax({
        url: ctx + 'system/behavior/types',
        type: 'GET',
        success: function(result) {
            if (result.code === 200 && result.data) {
                $.each(result.data, function(index, item) {
                    var selected = selectedValue && selectedValue === item.code ? 'selected' : '';
                    select.append('<option value="' + item.code + '" ' + selected + '>' + item.name + '</option>');
                });
            } else {
                // 使用默认行为类型
                $.each(behaviorTypeMap, function(key, value) {
                    var selected = selectedValue && selectedValue === key ? 'selected' : '';
                    select.append('<option value="' + key + '" ' + selected + '>' + value + '</option>');
                });
            }
        },
        error: function() {
            // 使用默认行为类型
            $.each(behaviorTypeMap, function(key, value) {
                var selected = selectedValue && selectedValue === key ? 'selected' : '';
                select.append('<option value="' + key + '" ' + selected + '>' + value + '</option>');
            });
        }
    });
}

/**
 * 加载小鼠选项
 */
function loadMouseOptions(selectId, selectedValue) {
    var select = $('#' + selectId);
    select.empty();
    select.append('<option value="">请选择小鼠</option>');
    
    $.ajax({
        url: ctx + 'system/mouse/list',
        type: 'GET',
        data: { status: 1 }, // 只加载正常状态的小鼠
        success: function(result) {
            if (result.code === 200 && result.rows) {
                $.each(result.rows, function(index, item) {
                    var selected = selectedValue && selectedValue == item.id ? 'selected' : '';
                    var text = item.mouseCode + ' - ' + item.mouseName;
                    select.append('<option value="' + item.id + '" ' + selected + '>' + text + '</option>');
                });
            }
        },
        error: function() {
            console.error('加载小鼠列表失败');
        }
    });
}

/**
 * 行为统计图表相关函数
 */
var BehaviorChart = {
    /**
     * 加载行为类型分布数据并初始化图表
     */
    loadTypeDistribution: function(containerId, options) {
        options = options || {};
        var params = {
            mouseCode: options.mouseCode || '',
            hours: options.hours || 24
        };
        
        // 如果指定了时间范围，使用具体时间
        if (options.startTime && options.endTime) {
            params.startTime = options.startTime;
            params.endTime = options.endTime;
            delete params.hours; // 删除hours参数，使用具体时间范围
        }
        
        $.ajax({
            url: ctx + 'system/behavior/type-distribution',
            type: 'GET',
            data: params,
            success: function(result) {
                if (result.code === 0) {
                    var chartData = result.data;
                    BehaviorChart.initTypeDistributionChart(containerId, chartData.data, {
                        title: '行为类型分布' + (params.mouseCode ? ' - ' + params.mouseCode : ''),
                        totalCount: chartData.totalCount,
                        queryTime: chartData.queryStartTime + ' 至 ' + chartData.queryEndTime
                    });
                } else {
                    $.modal.msgError('加载行为类型分布数据失败：' + result.msg);
                }
            },
            error: function() {
                $.modal.msgError('加载行为类型分布数据失败');
            }
        });
    },
    
    /**
     * 初始化行为趋势图
     */
    initTrendChart: function(containerId, data) {
        var option = {
            title: {
                text: '行为记录趋势',
                left: 'center'
            },
            tooltip: {
                trigger: 'axis',
                axisPointer: {
                    type: 'cross'
                }
            },
            legend: {
                data: ['行为记录数'],
                top: 30
            },
            grid: {
                left: '3%',
                right: '4%',
                bottom: '3%',
                containLabel: true
            },
            xAxis: {
                type: 'category',
                boundaryGap: false,
                data: data.dates || []
            },
            yAxis: {
                type: 'value'
            },
            series: [{
                name: '行为记录数',
                type: 'line',
                stack: 'Total',
                data: data.counts || []
            }]
        };
        
        var chart = echarts.init(document.getElementById(containerId));
        chart.setOption(option);
        return chart;
    },
    
    /**
     * 初始化行为类型分布饼图
     */
    initTypeDistributionChart: function(containerId, data, options) {
        options = options || {};
        var title = options.title || '行为类型分布';
        
        var option = {
            title: {
                text: title,
                left: 'center',
                subtext: options.queryTime ? '查询时间: ' + options.queryTime : '',
                subtextStyle: {
                    fontSize: 12,
                    color: '#666'
                }
            },
            tooltip: {
                trigger: 'item',
                formatter: function(params) {
                    var total = options.totalCount || 0;
                    return params.seriesName + '<br/>' + 
                           params.name + ': ' + params.value + ' 次 (' + params.percent + '%)';
                }
            },
            legend: {
                orient: 'vertical',
                left: 'left',
                top: 'middle'
            },
            series: [{
                name: '行为类型',
                type: 'pie',
                radius: '50%',
                data: data || [],
                emphasis: {
                    itemStyle: {
                        shadowBlur: 10,
                        shadowOffsetX: 0,
                        shadowColor: 'rgba(0, 0, 0, 0.5)'
                    }
                }
            }]
        };
        
        var chart = echarts.init(document.getElementById(containerId));
        chart.setOption(option);
        return chart;
    },
    
    /**
     * 初始化行为强度分布柱状图
     */
    initIntensityChart: function(containerId, data) {
        var option = {
            title: {
                text: '行为强度分布',
                left: 'center'
            },
            tooltip: {
                trigger: 'axis',
                axisPointer: {
                    type: 'shadow'
                }
            },
            grid: {
                left: '3%',
                right: '4%',
                bottom: '3%',
                containLabel: true
            },
            xAxis: {
                type: 'category',
                data: ['低', '中', '高']
            },
            yAxis: {
                type: 'value'
            },
            series: [{
                name: '记录数',
                type: 'bar',
                data: data || [0, 0, 0],
                itemStyle: {
                    color: function(params) {
                        var colors = ['#5cb85c', '#f0ad4e', '#d9534f'];
                        return colors[params.dataIndex];
                    }
                }
            }]
        };
        
        var chart = echarts.init(document.getElementById(containerId));
        chart.setOption(option);
        return chart;
    }
};

/**
 * 导出行为数据
 */
function exportBehaviorData(params) {
    $.modal.loading('正在导出数据，请稍候...');
    $.post(ctx + 'system/behavior/export', params, function(result) {
        if (result.code === 200) {
            $.modal.closeLoading();
            $.modal.msgSuccess('导出成功');
            window.location.href = ctx + 'common/download?fileName=' + encodeURI(result.msg) + '&delete=true';
        } else {
            $.modal.closeLoading();
            $.modal.alertError(result.msg);
        }
    });
}

/**
 * 批量删除行为记录
 */
function batchDeleteBehavior(ids) {
    $.modal.confirm('确认要删除选中的行为记录吗？', function() {
        $.ajax({
            url: ctx + 'system/behavior/remove',
            type: 'POST',
            data: { ids: ids.join(',') },
            success: function(result) {
                if (result.code === 200) {
                    $.modal.msgSuccess(result.msg);
                    if (typeof $.table !== 'undefined' && $.table.refresh) {
                        $.table.refresh();
                    }
                } else {
                    $.modal.alertError(result.msg);
                }
            },
            error: function() {
                $.modal.alertError('删除失败');
            }
        });
    });
}