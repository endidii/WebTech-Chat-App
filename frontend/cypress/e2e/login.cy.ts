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

describe('login page', () => {
    it('logs a user in', () => {
      cy.viewport(1920, 1080)
      cy.visit('http://localhost:5173/');

      cy.get('.sign-up-div').should("exist");

      cy.get('#email-input').type("max@gmail.com");
      cy.get('#password-input').type("maxpw");

      cy.get('.register-button').click();

      cy.url().should('include', '/users');
    })
});