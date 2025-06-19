import { defineConfig } from '@playwright/test';

export default defineConfig({
    testDir: './tests',
    timeout: 30 * 1000,
    use: {
        baseURL: 'http://localhost:8080',
        headless: true,
        screenshot: 'only-on-failure',
    },
    workers: 1,
    projects: [
        {
            name: 'chromium',
            use: { browserName: 'chromium' }
        }
    ]
});