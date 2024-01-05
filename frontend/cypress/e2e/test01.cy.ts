describe('template spec', () => {
  it('renders the login page', () => {
    cy.visit('http://localhost:5173/');

    cy.get('.blendr-name')
      .should("exist")
      .should("contain", "blendr");

    cy.get('.sign-up-div')
      .should("exist")
  })
})