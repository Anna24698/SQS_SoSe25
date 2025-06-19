import { test, expect } from '@playwright/test';

test('Registrieren funktioniert', async ({ page }) => {
    await page.goto('/auth/register');

    await page.fill('input[name="username"]', 'testuser');
    await page.fill('input[name="password"]', 'geheim123');
  //  await page.fill('input[name="confirmPassword"]', 'geheim123');

    await page.click('button[type="submit"]');

    // z.B. Weiterleitung oder Erfolgsmeldung prüfen
    await expect(page).toHaveURL('/auth/login');
});

test('Login funktioniert', async ({ page }) => {
    await page.goto('/auth/login');

    await page.fill('input[name="username"]', 'testuser');
    await page.fill('input[name="password"]', 'geheim123');
    await page.click('button[type="submit"]');

    // Erwartung: Umleitung zur Startseite
    await expect(page).toHaveURL('/buildDeck');
    await expect(page.locator('text=Logout')).toBeVisible();
});

test('Logout funktioniert', async ({ page }) => {
    // Einloggen vor Logout
    await page.goto('/auth/login');
    await page.fill('input[name="username"]', 'testuser');
    await page.fill('input[name="password"]', 'geheim123');
    await page.click('button[type="submit"]');
    await expect(page).toHaveURL('/buildDeck');

    // Logout-Link klicken
    await page.click('text=Logout');
    await expect(page).toHaveURL('/buildDeck');
    await expect(page.locator('text=Login')).toBeVisible();
    await expect(page.locator('text=Registrieren')).toBeVisible();
});