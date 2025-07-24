document.addEventListener('DOMContentLoaded', () => {

    // Spring Boot API'nizin adresi
    const API_URL = 'http://localhost:8080';

    // --- Sabit Değerler (Backend Enum'ları ile aynı olmalı) ---
    const PLATFORMS = ["WINDOWS", "LINUX", "MACOS"];
    const VERDICTS = ["GOLD", "BORKED", "NATIVE", "BRONZE", "PLATINUM", "SILVER"];
    const INSTABILITIES = ["STABLE", "FREQUENT_CRASHES", "UNPLAYABLE", "MINOR_ISSUES"];

    // --- Genel Durum (State) ---
    let allGames = [];
    let allReports = [];
    let selectedGameId = null;

    // --- DOM Elementleri ---
    const gamesListEl = document.getElementById('games-list');
    const welcomeMessageEl = document.getElementById('welcome-message');
    const gameDetailsViewEl = document.getElementById('game-details-view');
    const detailsPanelEl = document.getElementById('details-panel');
    const gameModalEl = document.getElementById('game-modal');
    const reportModalEl = document.getElementById('report-modal');
    const gameForm = document.getElementById('game-form');
    const reportForm = document.getElementById('report-form');

    // --- API İstek Yardımcısı ---
    const apiRequest = async (endpoint, method = 'GET', body = null) => {
        try {
            const options = {
                method,
                headers: { 'Content-Type': 'application/json' }
            };
            if (body) {
                options.body = JSON.stringify(body);
            }

            const response = await fetch(`${API_URL}${endpoint}`, options);

            if (!response.ok) {
                const errorText = await response.text();
                console.error('API Error Response:', errorText);
                throw new Error(`HTTP error! Status: ${response.status}`);
            }

            if (response.status === 204 || method === 'DELETE') {
                return null; // DELETE veya No Content durumlarında body olmaz
            }
            return await response.json();
        } catch (error) {
            console.error(`${API_URL}${endpoint} Error in API request to address:`, error);
            alert(`Unable to communicate with the backend. Make sure your Spring Boot application is running and that CORS settings are configured. Check the console (F12) for details.`);
            return null;
        }
    };
    
    // --- GÖRSELLEŞTİRME FONKSİYONLARI ---
    const renderGameList = () => {
        gamesListEl.innerHTML = '';
        allGames.sort((a, b) => a.name.localeCompare(b.name)).forEach(game => {
            const li = document.createElement('li');
            li.textContent = game.name;
            li.dataset.id = game.id;
            if (game.id === selectedGameId) li.classList.add('active');
            gamesListEl.appendChild(li);
        });
    };

    const renderSelectedGameDetails = () => {
        if (!selectedGameId) {
            welcomeMessageEl.style.display = 'block';
            gameDetailsViewEl.style.display = 'none';
            return;
        }
        const game = allGames.find(g => g.id === selectedGameId);
        if (!game) {
            selectedGameId = null;
            renderSelectedGameDetails();
            return;
        }
        welcomeMessageEl.style.display = 'none';
        gameDetailsViewEl.style.display = 'block';
        document.getElementById('selected-game-header').innerHTML = `<h2 data-text="${game.name}">${game.name}</h2><div class="header-actions"><button class="cybr-btn edit small" data-action="edit-game" data-id="${game.id}">Edit Game_</button><button class="cybr-btn delete small" data-action="delete-game" data-id="${game.id}">Delete Game_</button></div>`;
        document.getElementById('selected-game-info').innerHTML = `<div class="info-item"><strong>Developer</strong><span>${game.developer}</span></div><div class="info-item"><strong>Genre</strong><span>${game.genre}</span></div><div class="info-item"><strong>Deck Status</strong><span>${game.deckVerifiedStatus ? 'Verified' : 'Not Verified'}</span></div><div class="info-item"><strong>Platforms</strong><span>${game.platforms.join(', ')}</span></div>`;
        renderReportsForSelectedGame();
    };

    const renderReportsForSelectedGame = () => {
        const reportsForGameListEl = document.getElementById('reports-for-game-list');
        reportsForGameListEl.innerHTML = '';
        const filteredReports = allReports.filter(r => r.gameId === selectedGameId);
        if (filteredReports.length === 0) {
            reportsForGameListEl.innerHTML = '<li>// No field report found. Be the first to add one.</li>';
            return;
        }
        filteredReports.forEach(report => {
            const li = document.createElement('li');
            li.innerHTML = `<div class="report-content"><strong>Verdict: <span style="color: var(--accent-lime);">${report.verdict}</span></strong> (Overall: ${report.overallRating})<p>${report.body}</p><small>// System: ${report.distro || 'N/A'} | ${report.cpuModel || 'N/A'} | ${report.gpuModel || 'N/A'}</small></div><div class="report-actions"><button class="cybr-btn edit tiny" data-action="edit-report" data-id="${report.id}">Edit</button><button class="cybr-btn delete tiny" data-action="delete-report" data-id="${report.id}">Del</button></div>`;
            reportsForGameListEl.appendChild(li);
        });
    };

    // --- ÇEKİRDEK MANTIK & EYLEMLER ---
    const selectGame = (gameId) => {
        selectedGameId = gameId;
        renderGameList();
        renderSelectedGameDetails();
    };

    const deleteGame = async (gameId) => {
        if (confirm(`// Confirm deletion protocol for game ID ${gameId}. \n// This action cannot be undone.`)){
            await apiRequest(`/games/${gameId}`, 'DELETE');
            selectedGameId = null;
            await initializeApp();
        }
    };

    const deleteReport = async (reportId) => {
        if (confirm(`// Confirm the deletion protocol for the report with ID ${reportId}.`)) {
            await apiRequest(`/reports/${reportId}`, 'DELETE');
            allReports = await apiRequest('/reports') || [];
            renderReportsForSelectedGame();
        }
    };

    // --- MODALLAR & FORMLAR ---
    const populateCheckboxes = (container, values) => {
        container.innerHTML = '';
        values.forEach(value => {
            container.innerHTML += `<label><input type="checkbox" name="${container.id}" value="${value}">${value}</label>`;
        });
    };

    const populateSelect = (selectElement, values, defaultText) => {
        selectElement.innerHTML = `<option value="">-- ${defaultText} --</option>`;
        values.forEach(value => {
            selectElement.innerHTML += `<option value="${value}">${value}</option>`;
        });
    };

    const openGameModal = async (gameId = null) => {
        gameForm.reset();
        document.getElementById('game-modal-title').textContent = gameId ? 'Edit Game Data' : 'Write New Game Data';
        gameForm.dataset.editId = gameId || '';
        if (gameId) {
            const game = await apiRequest(`/games/${gameId}`);
            if (game) {
                document.getElementById('game-name').value = game.name;
                document.getElementById('game-developer').value = game.developer;
                document.getElementById('game-genre').value = game.genre;
                document.getElementById('game-deckVerifiedStatus').value = String(game.deckVerifiedStatus);
                document.querySelectorAll('#game-platforms input').forEach(cb => {
                    cb.checked = game.platforms.includes(cb.value);
                });
            }
        }
        gameModalEl.style.display = 'flex';
    };

    gameForm.addEventListener('submit', async (e) => {
        e.preventDefault();
        const editId = e.target.dataset.editId;
        const gameData = {
            id: editId ? parseInt(editId) : null,
            name: document.getElementById('game-name').value,
            developer: document.getElementById('game-developer').value,
            genre: document.getElementById('game-genre').value,
            platforms: Array.from(document.querySelectorAll('#game-platforms input:checked')).map(cb => cb.value),
            deckVerifiedStatus: document.getElementById('game-deckVerifiedStatus').value === 'true'
        };
        if (editId) {
            await apiRequest(`/games/${editId}`, 'PUT', gameData);
        } else {
            await apiRequest('/games', 'POST', gameData);
        }
        gameModalEl.style.display = 'none';
        await initializeApp();
    });

    const openReportModal = async (reportId = null) => {
        reportForm.reset();
        document.getElementById('report-modal-title').textContent = reportId ? 'Edit Field Report' : 'Write New Field Report';
        reportForm.dataset.editId = reportId || '';

        const gameSelector = document.getElementById('report-gameId');
        gameSelector.innerHTML = '';
        allGames.forEach(game => {
            gameSelector.innerHTML += `<option value="${game.id}">${game.name}</option>`;
        });

        if (reportId) {
            const report = await apiRequest(`/reports/${reportId}`);
            if (report) {
                gameSelector.value = report.gameId;
                gameSelector.disabled = true;
                Object.keys(report).forEach(key => {
                    const el = document.getElementById(`report-${key}`);
                    if (el) el.value = report[key];
                });
            }
        } else {
            if (!selectedGameId) {
                alert('// ERROR: Please select a game from the library before adding a report..');
                return;
            }
            gameSelector.disabled = false;
            gameSelector.value = selectedGameId;
        }
        reportModalEl.style.display = 'flex';
    };

    reportForm.addEventListener('submit', async (e) => {
        e.preventDefault();
        const editId = e.target.dataset.editId;
        const reportData = {
            id: editId ? parseInt(editId) : null,
            gameId: parseInt(document.getElementById('report-gameId').value),
            body: document.getElementById('report-body').value,
            verdict: document.getElementById('report-verdict').value,
            instability: document.getElementById('report-instability').value,
            multiplayerRating: document.getElementById('report-multiplayerRating').value,
            overallRating: document.getElementById('report-overallRating').value,
            distro: document.getElementById('report-distro').value,
            kernel: document.getElementById('report-kernel').value,
            ramGb: parseInt(document.getElementById('report-ramGb').value),
            gpuDriver: document.getElementById('report-gpuDriver').value,
            gpuModel: document.getElementById('report-gpuModel').value,
            cpuModel: document.getElementById('report-cpuModel').value,
        };
        if (editId) {
            await apiRequest(`/reports/${editId}`, 'PUT', reportData);
        } else {
            await apiRequest('/reports', 'POST', reportData);
        }
        reportModalEl.style.display = 'none';
        allReports = await apiRequest('/reports') || [];
        renderReportsForSelectedGame();
    });

    // --- OLAY DİNLEYİCİLERİ ---
    gamesListEl.addEventListener('click', (e) => {
        if (e.target.tagName === 'LI') selectGame(parseInt(e.target.dataset.id));
    });

    detailsPanelEl.addEventListener('click', (e) => {
        const target = e.target.closest('button');
        if (!target) return;
        const action = target.dataset.action;
        const id = parseInt(target.dataset.id);
        switch (action) {
            case 'edit-game': openGameModal(id); break;
            case 'delete-game': deleteGame(id); break;
            case 'add-report': openReportModal(); break;
            case 'edit-report': openReportModal(id); break;
            case 'delete-report': deleteReport(id); break;
        }
    });

    document.getElementById('add-game-btn').addEventListener('click', () => openGameModal());
    document.getElementById('close-game-modal-btn').addEventListener('click', () => gameModalEl.style.display = 'none');
    document.getElementById('close-report-modal-btn').addEventListener('click', () => reportModalEl.style.display = 'none');

    // --- BAŞLATMA ---
    const initializeApp = async () => {
        populateCheckboxes(document.getElementById('game-platforms'), PLATFORMS);
        populateSelect(document.getElementById('report-verdict'), VERDICTS, "Select Verdict");
        populateSelect(document.getElementById('report-instability'), INSTABILITIES, "Select Instability");

        const [gamesData, reportsData] = await Promise.all([
            apiRequest('/games'),
            apiRequest('/reports')
        ]);
        allGames = gamesData || [];
        allReports = reportsData || [];
        renderGameList();
        renderSelectedGameDetails();
    };

    initializeApp();
});