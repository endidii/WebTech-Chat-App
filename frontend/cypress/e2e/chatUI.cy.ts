describe('create textchannel', () => {
  it('creates a textchannel', () => {
    cy.viewport(1920, 1080)
    cy.visit('http://localhost:5173/users/75593');
    cy.get('#add-channel-button').click();
    cy.get('.modal').should("exist");
    cy.get('.modal-content').should("exist");

    cy.get('#name-input').type("testchannel2");
    cy.get('#description-input').type("testdescription");

    cy.get('.create-channel-button').click();

    cy.get('.channel-button').should("exist").should("contain.text", "testchannel2");

    cy.get(':nth-child(7) > .chat-tag').click();
    cy.get('.member').should("exist").should("contain.text", "max");
  })
});