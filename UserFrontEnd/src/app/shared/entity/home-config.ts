import {Device} from './device';

export interface HomeConfig{
    id:string;
    name:string;

    devices?:Device[]
}
