// Game state
let targetColor;
let lives = 3;
let score = 0;
const colorCount = 3;

// DOM elements
const colorOptions = document.getElementById('color-options');
const targetColorDisplay = document.getElementById('target-color');
const feedback = document.getElementById('feedback');
const livesDisplay = document.getElementById('lives');
const scoreDisplay = document.getElementById('score');
const replayBtn = document.getElementById('replay-btn');

// Generate random RGB color
function generateRGB() {
    const r = Math.floor(Math.random() * 256);
    const g = Math.floor(Math.random() * 256);
    const b = Math.floor(Math.random() * 256);
    return `rgb(${r}, ${g}, ${b})`;
}

// Create color options
function createColorOptions() {
    colorOptions.innerHTML = '';
    const colors = [targetColor];
    
    // Generate additional random colors
    while (colors.length < colorCount) {
        const newColor = generateRGB();
        if (!colors.includes(newColor)) colors.push(newColor);
    }
    
    // Shuffle colors and create elements
    colors.sort(() => Math.random() - 0.5).forEach(color => {
        const colorDiv = document.createElement('div');
        colorDiv.className = 'color-box';
        colorDiv.style.backgroundColor = color;
        colorDiv.addEventListener('click', () => handleGuess(color));
        colorOptions.appendChild(colorDiv);
    });
}

// Handle color selection
function handleGuess(selectedColor) {
    if (lives <= 0) return;

    if (selectedColor === targetColor) {
        score++;
        scoreDisplay.textContent = score;
        showFeedback('Correct! +1 point', 'correct');
        newRound();
    } else {
        lives--;
        livesDisplay.textContent = lives;
        showFeedback('Wrong! Try again', 'incorrect');
        if (lives <= 0) endGame();
    }
}

// Start new round
function newRound() {
    targetColor = generateRGB();
    targetColorDisplay.style.backgroundColor = targetColor;
    createColorOptions();
}

// Show feedback message
function showFeedback(text, type) {
    feedback.textContent = text;
    feedback.className = `feedback-visible ${type}`;
    setTimeout(() => {
        feedback.className = 'feedback-hidden';
    }, 1500);
}

// End game sequence
function endGame() {
    colorOptions.innerHTML = '';
    feedback.textContent = `Game Over! Final Score: ${score}`;
    feedback.className = 'feedback-visible incorrect';
    replayBtn.classList.remove('hidden');
}

// Initialize game
function initGame() {
    lives = 3;
    score = 0;
    livesDisplay.textContent = lives;
    scoreDisplay.textContent = score;
    feedback.className = 'feedback-hidden';
    replayBtn.classList.add('hidden');
    newRound();
}

// Event listeners
replayBtn.addEventListener('click', initGame);

// Start game
initGame();