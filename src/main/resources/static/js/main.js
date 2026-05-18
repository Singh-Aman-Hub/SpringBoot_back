async function fetchItems() {
    try {
        const response = await fetch('/api/items');
        if (!response.ok) {
            throw new Error('Network response was not ok');
        }
        const items = await response.json();
        
        const listDiv = document.getElementById('itemsList');
        if (items.length === 0) {
            listDiv.innerHTML = '<p>No items found.</p>';
            return;
        }

        let html = '<ul style="list-style: none; padding: 10px 0;">';
        items.forEach(item => {
            html += `<li style="padding: 10px; border-bottom: 1px solid #ccc;">
                <strong>ID:</strong> ${item.id} <br/>
                <strong>Title:</strong> ${item.title || 'N/A'} <br/>
                <strong>Type:</strong> ${item.type || 'N/A'} <br/>
                <strong>Category:</strong> ${item.category || 'N/A'} <br/>
                <strong>Description:</strong> ${item.description || 'N/A'}
            </li>`;
        });
        html += '</ul>';
        
        listDiv.innerHTML = html;
    } catch (error) {
        console.error('Error fetching items:', error);
        document.getElementById('itemsList').innerHTML = '<p style="color:red;">Error loading items.</p>';
    }
}

async function fetchUsers() {
    try {
        const response = await fetch('/api/users');
        if (!response.ok) {
            throw new Error('Network response was not ok');
        }
        const data = await response.json();
        
        const listDiv = document.getElementById('usersList');
        if (data.length === 0) {
            listDiv.innerHTML = '<p>No users found.</p>';
            return;
        }

        let html = '<ul style="list-style: none; padding: 10px 0;">';
        data.forEach(user => {
            html += `<li style="padding: 10px; border-bottom: 1px solid #ccc;">
                <strong>ID:</strong> ${user.id} - <strong>Name:</strong> ${user.name || user.username || 'N/A'}
            </li>`;
        });
        html += '</ul>';
        listDiv.innerHTML = html;
    } catch (error) {
        console.error('Error fetching users:', error);
        document.getElementById('usersList').innerHTML = '<p style="color:red;">Error loading users.</p>';
    }
}

async function fetchClaims() {
    try {
        const response = await fetch('/api/claims');
        if (!response.ok) {
            throw new Error('Network response was not ok');
        }
        const data = await response.json();
        
        const listDiv = document.getElementById('claimsList');
        if (data.length === 0) {
            listDiv.innerHTML = '<p>No claims found.</p>';
            return;
        }

        let html = '<ul style="list-style: none; padding: 10px 0;">';
        data.forEach(claim => {
            html += `<li style="padding: 10px; border-bottom: 1px solid #ccc;">
                <strong>Claim ID:</strong> ${claim.id} - <strong>Proof:</strong> ${claim.proofDescription || 'N/A'}
            </li>`;
        });
        html += '</ul>';
        listDiv.innerHTML = html;
    } catch (error) {
        console.error('Error fetching claims:', error);
        document.getElementById('claimsList').innerHTML = '<p style="color:red;">Error loading claims.</p>';
    }
}

async function addUser() {
    const name = document.getElementById('userName').value;
    const email = document.getElementById('userEmail').value;
    const role = document.getElementById('userRole').value;

    if (!name || !email) return alert('Name and email are required');

    try {
        const res = await fetch('/api/users', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ name, email, role })
        });
        if (res.ok) {
            alert('User added successfully');
            document.getElementById('userName').value = '';
            document.getElementById('userEmail').value = '';
            fetchUsers();
        } else {
            alert('Failed to add user');
        }
    } catch (error) {
        console.error(error);
        alert('Error adding user');
    }
}

async function addItem() {
    const title = document.getElementById('itemTitle').value;
    const description = document.getElementById('itemDescription').value;
    const category = document.getElementById('itemCategory').value;
    const type = document.getElementById('itemType').value;

    if (!title) return alert('Title is required');

    try {
        const res = await fetch('/api/items', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ title, description, category, type })
        });
        if (res.ok) {
            alert('Item added successfully');
            document.getElementById('itemTitle').value = '';
            document.getElementById('itemDescription').value = '';
            document.getElementById('itemCategory').value = '';
            fetchItems();
        } else {
            alert('Failed to add item');
        }
    } catch (error) {
        console.error(error);
        alert('Error adding item');
    }
}

async function addClaim() {
    const itemId = document.getElementById('claimItemId').value;
    const userId = document.getElementById('claimUserId').value;
    const proofDescription = document.getElementById('claimProof').value;

    if (!itemId || !userId) return alert('Item ID and User ID are required');

    try {
        // Constructing object correctly based on typical relationship formats.
        // Assuming @ManyToOne associations require an object wrapper with id like { id: 1 } 
        const payload = {
            item: { id: parseInt(itemId) },
            claimant: { id: parseInt(userId) },
            proofDescription
        };

        const res = await fetch('/api/claims', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(payload)
        });
        if (res.ok) {
            alert('Claim added successfully');
            document.getElementById('claimItemId').value = '';
            document.getElementById('claimUserId').value = '';
            document.getElementById('claimProof').value = '';
            fetchClaims();
        } else {
            alert('Failed to add claim');
        }
    } catch (error) {
        console.error(error);
        alert('Error adding claim');
    }
}
