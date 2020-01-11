import { DatePipe } from '@angular/common';

export class Result{
    id: string;
    description: string;
    date: string;
    constructor(private Description: any){
        this.description = Description;
    }
}