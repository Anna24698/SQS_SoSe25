/*import { chromium } from '@playwright/test';
//import fs from 'fs';

export default async () => {
    const browser = await chromium.launch();
    const context1 = await browser.newContext();
    const page1 = await context1.newPage();

    await page1.goto('http://localhost:8080/auth/register');
    await page1.fill('input[name="username"]', 'testuser1');
    await page1.fill('input[name="password"]', 'geheim123');
    await page1.click('button[type="submit"]');

    await page1.goto('http://localhost:8080/auth/login');
    await page1.fill('input[name="username"]', 'testuser1');
    await page1.fill('input[name="password"]', 'geheim123');
    await page1.click('button[type="submit"]');

    await context1.storageState({ path: 'storage/user1.json' });

    const context2 = await browser.newContext();
    const page2 = await context2.newPage();

    await page2.goto('http://localhost:8080/auth/register');
    await page2.fill('input[name="username"]', 'testuser2');
    await page2.fill('input[name="password"]', 'geheim123');
    await page2.click('button[type="submit"]');

    await page2.goto('http://localhost:8080/auth/login');
    await page2.fill('input[name="username"]', 'testuser2');
    await page2.fill('input[name="password"]', 'geheim123');
    await page2.click('button[type="submit"]');

    await context2.storageState({ path: 'storage/user2.json' });

    await browser.close();
};*/