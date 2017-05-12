import { FortuneCookieAppPage } from './app.po';

describe('fortune-cookie-app App', () => {
  let page: FortuneCookieAppPage;

  beforeEach(() => {
    page = new FortuneCookieAppPage();
  });

  it('should display message saying app works', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('app works!');
  });
});
