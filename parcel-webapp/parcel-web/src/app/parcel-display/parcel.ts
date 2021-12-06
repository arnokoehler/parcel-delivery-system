import {Receipient} from "./receipient";

export interface Parcel {
    id: string;
    name: string;
    receipient: Receipient,
    weight: number,
    value: number
}
