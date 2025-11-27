<%--
  Created by IntelliJ IDEA.
  User: 32029
  Date: 2025/11/20
  Time: 20:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>趣味功能 - 发财模拟器</title>
    <style>
        body {
            font-family: 'Arial', sans-serif;
            margin: 0;
            padding: 20px;
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            min-height: 100vh;
        }
        .container {
            max-width: 800px;
            margin: 0 auto;
            background: rgba(255, 255, 255, 0.9);
            border-radius: 15px;
            padding: 30px;
            box-shadow: 0 10px 30px rgba(0, 0, 0, 0.3);
        }
        h1 {
            text-align: center;
            color: #FFD700;
            font-size: 2.5em;
            text-shadow: 2px 2px 4px rgba(0, 0, 0, 0.3);
            margin-bottom: 30px;
        }
        .fortune-box {
            background: linear-gradient(45deg, #FFD700, #FFA500);
            border-radius: 10px;
            padding: 20px;
            text-align: center;
            margin: 20px 0;
            box-shadow: 0 5px 15px rgba(0, 0, 0, 0.2);
        }
        .fortune-text {
            font-size: 1.5em;
            font-weight: bold;
            color: #8B4513;
        }
        .gold-btn {
            background: linear-gradient(45deg, #FFD700, #FFA500);
            color: #8B4513;
            border: none;
            padding: 15px 30px;
            font-size: 1.2em;
            border-radius: 50px;
            cursor: pointer;
            display: block;
            margin: 30px auto;
            font-weight: bold;
            box-shadow: 0 5px 15px rgba(0, 0, 0, 0.2);
            transition: all 0.3s ease;
        }
        .gold-btn:hover {
            transform: scale(1.05);
            box-shadow: 0 8px 20px rgba(0, 0, 0, 0.3);
        }
        .gold-rain {
            position: fixed;
            top: -50px;
            font-size: 2em;
            animation: fall linear forwards;
            z-index: 1000;
        }
        @keyframes fall {
            to {
                transform: translateY(105vh);
            }
        }
        .back-link {
            text-align: center;
            margin-top: 20px;
        }
        .back-link a {
            color: #4CAF50;
            text-decoration: none;
            font-weight: bold;
        }
        .back-link a:hover {
            text-decoration: underline;
        }
        /* 用户名输入框样式 */
        .username-form {
            text-align: center;
            margin: 20px 0;
        }
        .username-form input[type="text"] {
            padding: 12px;
            font-size: 1.1em;
            border: 2px solid #FFD700;
            border-radius: 30px;
            width: 300px;
            text-align: center;
            outline: none;
        }
        .username-form input[type="text"]:focus {
            border-color: #FFA500;
            box-shadow: 0 0 10px rgba(255, 215, 0, 0.5);
        }
        .personalized-fortune {
            background: linear-gradient(45deg, #90EE90, #32CD32);
            border-radius: 10px;
            padding: 20px;
            margin: 20px 0;
            text-align: center;
            box-shadow: 0 5px 15px rgba(0, 0, 0, 0.2);
        }
        .personalized-fortune h3 {
            color: #006400;
            margin-top: 0;
        }
    </style>
</head>
<body>
<div class="container">
    <h1>💰 发财模拟器 💰</h1>
    
    <div class="username-form">
        <input type="text" id="username" placeholder="输入你的名字获得个性化财运预测">
        <button class="gold-btn" onclick="generatePersonalizedFortune()">获取个性化预测</button>
    </div>
    
    <div class="personalized-fortune" id="personalizedFortune" style="display: none;">
        <h3>专属财运预测</h3>
        <div id="personalizedFortuneText"></div>
    </div>
    
    <div class="fortune-box">
        <div class="fortune-text" id="fortuneText">
            点击下面的发财按钮，看看你的财运如何！
        </div>
    </div>
    
    <button class="gold-btn" onclick="generateFortune()">💸 点击发财 💸</button>
    
    <div class="back-link">
        <a href="index.jsp">← 返回首页</a>
    </div>
</div>

<script>
    const fortunes = [
        "🎉 恭喜发财！今年你将获得意外之财！",
        "💰 财神到你家，金银滚滚来！",
        "🌟 事业蒸蒸日上，财富源源不断！",
        "🎊 投资眼光独到，收益翻倍增长！",
        "💎 贵人相助，财源广进！",
        "🏆 商机不断，日进斗金！",
        "🍀 运势如虹，富贵满堂！",
        "📈 股市大红，收益爆棚！"
    ];
    
    const personalizedTemplates = [
        "亲爱的{name}，您的财运如日中天，即将迎来一波财富浪潮！",
        "{name}，您天生就是招财体质，今年将有三波财运降临！",
        "尊敬的{name}，您的努力即将开花结果，财富之门已经为您打开！",
        "{name}，贵人将为您带来意想不到的财富机会，敬请期待！",
        "亲爱的{name}，您的投资眼光将为您带来丰厚回报，把握机会！"
    ];
    
    function generateFortune() {
        const fortuneText = document.getElementById('fortuneText');
        const randomFortune = fortunes[Math.floor(Math.random() * fortunes.length)];
        fortuneText.textContent = randomFortune;
        
        // 创建金币雨效果
        createGoldRain();
    }
    
    function generatePersonalizedFortune() {
        const username = document.getElementById('username').value.trim();
        if (!username) {
            alert('请输入您的名字！');
            return;
        }
        
        const personalizedFortuneDiv = document.getElementById('personalizedFortune');
        const personalizedFortuneText = document.getElementById('personalizedFortuneText');
        
        const randomTemplate = personalizedTemplates[Math.floor(Math.random() * personalizedTemplates.length)];
        const personalizedFortune = randomTemplate.replace('{name}', username);
        
        personalizedFortuneText.textContent = personalizedFortune;
        personalizedFortuneDiv.style.display = 'block';
        
        // 创建金币雨效果
        createGoldRain();
    }
    
    function createGoldRain() {
        const container = document.querySelector('.container');
        const goldCount = 50;
        
        for (let i = 0; i < goldCount; i++) {
            setTimeout(() => {
                const gold = document.createElement('div');
                gold.innerHTML = '💰';
                gold.className = 'gold-rain';
                gold.style.left = Math.random() * 100 + 'vw';
                gold.style.animationDuration = (Math.random() * 3 + 2) + 's';
                gold.style.opacity = Math.random() * 0.5 + 0.5;
                document.body.appendChild(gold);
                
                // 动画结束后移除元素
                setTimeout(() => {
                    gold.remove();
                }, 5000);
            }, i * 100);
        }
    }
    
    // 允许按回车键触发个性化预测
    document.getElementById('username').addEventListener('keypress', function(e) {
        if (e.key === 'Enter') {
            generatePersonalizedFortune();
        }
    });
</script>
</body>
</html>