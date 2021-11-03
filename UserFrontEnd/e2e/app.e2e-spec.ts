import {Pk2AngularCliFreePage} from './app.po';

describe('pk2-angular-cli-free App', () => {
  let page: Pk2AngularCliFreePage;

  beforeEach(() => {
    page = new Pk2AngularCliFreePage();
  });

  it('should display welcome message', () => {
    page.navigateTo();
    // @ts-ignore
    expect(page.getParagraphText()).toEqual('Welcome to app!!');
  });
});
