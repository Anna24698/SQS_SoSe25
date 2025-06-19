import { test, expect } from '@playwright/test';


test.beforeEach(async ({ page }) => {
    //Sicherstellen, dass User existiert
    await page.goto('/auth/register');
    await page.fill('input[name="username"]', 'testuser2');
    await page.fill('input[name="password"]', 'geheim123');
    await page.click('button[type="submit"]');

    //User anmelden
    await page.goto('/auth/login');
    await page.fill('input[name="username"]', 'testuser2');
    await page.fill('input[name="password"]', 'geheim123');
    await page.click('button[type="submit"]');

});

test('Kartenpool anzeigen und Karte zum Deck hinzufügen', async ({ page }) => {

    await expect(page).toHaveURL('/buildDeck');

    // Erste Karte im Kartenpool anklicken
    const ersteKarte = page.locator('.selectable-card').first();
    await ersteKarte.click();

    // Prüfen, ob Karte jetzt im Deck ist (im Container #deck)
    const deckBilder = page.locator('#deck img');
    const anzahl = await deckBilder.count();
    expect(anzahl).toBeGreaterThan(0);
});

test('Deck speichern', async ({ page }) => {

    await expect(page).toHaveURL('/buildDeck');

    // Karte hinzufügen
    await page.locator('.selectable-card').first().click();

    // Deck speichern Button klicken
    await page.click('#submitDeck');

    // Erfolgsmeldung prüfen (Alert)
    page.on('dialog', dialog => {
        expect(dialog.message()).toContain('erfolgreich gespeichert');
        dialog.accept();
    });
});

test('Deck mit Anführer speichern', async ({ page }) => {
    // Login und Deckbau wie oben
    await expect(page).toHaveURL('/buildDeck');
    await page.getByRole('button', { name: 'Anführer wählen' }).click();
    await page.locator('.modal-body > .d-flex > div:nth-child(17) > .img-thumbnail').click();
    await page.getByRole('button', { name: 'Anführer auswählen' }).click();
    await page.locator('div:nth-child(263) > .img-thumbnail').click();
    await page.locator('div:nth-child(261) > .img-thumbnail').click();
    /*page.once('dialog', dialog => {
        console.log(`Dialog message: ${dialog.message()}`);
        dialog.dismiss().catch(() => {});
    });*/
    await page.getByRole('button', { name: 'Deck speichern' }).click();


    // Erfolgsmeldung prüfen (Alert)
    page.on('dialog', dialog => {
        expect(dialog.message()).toContain('erfolgreich gespeichert');
        dialog.accept();
    });
});