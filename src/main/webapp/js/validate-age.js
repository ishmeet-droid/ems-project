function validateAge() {
    const ageInput = document.getElementById('ageField');
    const age = parseInt(ageInput.value, 10);

    if (isNaN(age) || age < 18 || age > 60) {
        alert('Age must be a number between 18 and 60.');
        ageInput.focus();
        return false;
    }
    return true;
}
