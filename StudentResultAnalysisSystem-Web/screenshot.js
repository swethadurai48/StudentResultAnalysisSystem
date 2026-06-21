import puppeteer from 'puppeteer';

(async () => {
  console.log('Launching browser...');
  const browser = await puppeteer.launch();
  const page = await browser.newPage();
  await page.setViewport({ width: 1440, height: 900 });

  // Dashboard
  console.log('Capturing Dashboard...');
  await page.goto('http://localhost:3001/dashboard', { waitUntil: 'networkidle0' });
  await new Promise(r => setTimeout(r, 2000)); // wait for charts to animate
  await page.screenshot({ path: '../assets/dashboard.png' });

  // Students
  console.log('Capturing Students...');
  await page.goto('http://localhost:3001/students', { waitUntil: 'networkidle0' });
  await new Promise(r => setTimeout(r, 1000));
  await page.screenshot({ path: '../assets/students.png' });

  // Subjects
  console.log('Capturing Subjects...');
  await page.goto('http://localhost:3001/subjects', { waitUntil: 'networkidle0' });
  await new Promise(r => setTimeout(r, 1000));
  await page.screenshot({ path: '../assets/subjects.png' });

  // Marks
  console.log('Capturing Marks...');
  await page.goto('http://localhost:3001/marks', { waitUntil: 'networkidle0' });
  await new Promise(r => setTimeout(r, 1000));
  await page.screenshot({ path: '../assets/marks.png' });

  await browser.close();
  console.log('Screenshots saved successfully!');
})();
