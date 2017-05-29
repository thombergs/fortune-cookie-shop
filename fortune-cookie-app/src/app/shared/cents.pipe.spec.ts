import { CentsPipe } from './cents.pipe';

describe('CentsPipe', () => {
  it('create an instance', () => {
    const pipe = new CentsPipe();
    expect(pipe).toBeTruthy();
  });
});
