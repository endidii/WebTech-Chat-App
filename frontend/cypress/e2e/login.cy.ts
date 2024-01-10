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

describe('wrong password', () => {
  it('shows error when entering a wrong password (login)', () => {
    cy.viewport(1920, 1080)
    cy.visit('http://localhost:5173/');

    cy.get('.sign-up-div').should("exist");

    cy.get('#email-input').type("max@gmail.com");
    cy.get('#password-input').type("maxpw1235");

    cy.get('.register-button').click();

    cy.get('.error-message').should("exist").should("contain.text", "Invalid password");
  })
});

describe('no user with this email', () => {
  it('shows error when entering an email which is not registered (login)', () => {
    cy.viewport(1920, 1080)
    cy.visit('http://localhost:5173/');

    cy.get('.sign-up-div').should("exist");

    cy.get('#email-input').type("m123@gmail.com");
    cy.get('#password-input').type("maxpw12325");

    cy.get('.register-button').click();
    cy.get('.error-message').should("exist").should("contain.text", "User with email m123@gmail.com does not exist.");
  })
});