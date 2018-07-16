import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EditCompComponentComponent } from './edit-comp-component.component';

describe('EditCompComponentComponent', () => {
  let component: EditCompComponentComponent;
  let fixture: ComponentFixture<EditCompComponentComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EditCompComponentComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EditCompComponentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
