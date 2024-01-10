// api schnittstelle nur fürs testen: delete all
// oder test user anlegen und dann löschen
// javascript library benutzen für random email generator

describe('registers a user', () => {
  it('registers a user and shows ChatUI', () => {
    cy.viewport(1920, 1080)
    cy.visit('http://localhost:5173/');

    cy.get('a').click();

    cy.get('.sign-up-div').should("exist");

    cy.get('#name-input').type("max123");
    cy.get('#email-input').type("max123@gmail.com");
    cy.get('#password-input').type("maxpw123");

    cy.get('.register-button').click();

    cy.url().should('include', '/users');
  })
});

describe('user already exists', () => {
  it('shows error when registering a user which already exists', () => {
    cy.viewport(1920, 1080)
    cy.visit('http://localhost:5173/');

    cy.get('a').click();

    cy.get('.sign-up-div').should("exist");

    cy.get('#name-input').type("max");
    cy.get('#email-input').type("max@gmail.com");
    cy.get('#password-input').type("maxpw");

    cy.get('.register-button').click();

    cy.get('.error-message').should("exist").should("contain.text", "max@gmail.com");
  })
});