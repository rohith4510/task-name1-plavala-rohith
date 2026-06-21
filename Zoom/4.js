// Local In-Memory Dataset acting as state tracker
let bookCatalog = [
    { id: 1001, title: "Architectural Integrity Vol 1", author: "Martina Plantijn", genre: "Full Stack Logic" },
    { id: 1002, title: "Computing Machinery and Logic", author: "Alan Turing", genre: "Computer Science" }
];

// Cache UI Elements
const resourceForm = document.getElementById('resourceForm');
const bookTitleInput = document.getElementById('bookTitle');
const bookAuthorInput = document.getElementById('bookAuthor');
const bookGenreInput = document.getElementById('bookGenre');
const catalogTableBody = document.getElementById('catalogTableBody');
const recordCounter = document.getElementById('recordCounter');

// Initialize Dashboard Rendering
document.addEventListener('DOMContentLoaded', renderCatalog);

// Main Renderer Function
function renderCatalog() {
    catalogTableBody.innerHTML = '';
    
    bookCatalog.forEach(book => {
        const row = document.createElement('tr');
        row.innerHTML = `
            <td><strong>#${book.id}</strong></td>
            <td>${escapeHTML(book.title)}</td>
            <td>${escapeHTML(book.author)}</td>
            <td><span class="badge" style="background-color: #f1f5f9; color: #334155;">${book.genre}</span></td>
        `;
        catalogTableBody.appendChild(row);
    });

    // Sync state counter metrics
    recordCounter.textContent = `${bookCatalog.length} Records Persisted`;
}

// Intercept Submit Routine for Validation Handling
resourceForm.addEventListener('submit', function(e) {
    e.preventDefault();
    
    let isTitleValid = validateField(bookTitleInput, 'titleError');
    let isAuthorValid = validateField(bookAuthorInput, 'authorError');
    
    if (isTitleValid && isAuthorValid) {
        // Instantiate data schema map matching target architectures
        const newBook = {
            id: bookCatalog.length > 0 ? Math.max(...bookCatalog.map(b => b.id)) + 1 : 1001,
            title: bookTitleInput.value.trim(),
            author: bookAuthorInput.value.trim(),
            genre: bookGenreInput.value
        };
        
        // Push object structure down database emulation pipeline
        bookCatalog.push(newBook);
        
        // Update view layers
        renderCatalog();
        
        // Reset inputs
        resourceForm.reset();
    }
});

// Realtime individual parameter integrity checker
function validateField(inputElement, errorElementId) {
    const errorTarget = document.getElementById(errorElementId);
    if (!inputElement.value.trim()) {
        inputElement.classList.add('invalid');
        errorTarget.style.display = 'block';
        return false;
    } else {
        inputElement.classList.remove('invalid');
        errorTarget.style.display = 'none';
        return true;
    }
}

// Guard logic protecting pipeline execution states from script injection
function escapeHTML(str) {
    return str.replace(/[&<>'"]/g, 
        tag => ({ '&': '&amp;', '<': '&lt;', '>': '&gt;', "'": '&#39;', '"': '&quot;' }[tag] || tag)
    );
}