import {expect} from "vitest";
import "@testing-library/jest-dom/vitest"
import * as matchers from "@testing-library/jest-dom/matchers"
import ResizeObserver from "resize-observer-polyfill"

globalThis.ResizeObserver = ResizeObserver

expect.extend(matchers)